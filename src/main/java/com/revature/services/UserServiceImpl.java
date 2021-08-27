 package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Item;
import com.revature.beans.User;
import com.revature.data.ReactiveItemDao;
import com.revature.data.ReactiveUserDao;
import com.revature.dto.UserDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
public class UserServiceImpl implements UserService {
	
	private ReactiveUserDao userDao;
	private ReactiveItemDao itemDao;
	
	@Autowired
	public UserServiceImpl(ReactiveUserDao userDao) {
		super();
		this.userDao = userDao;
	}
	
	@Override
	public Mono<User> login(String username) {
		
		Mono<User> userMono = userDao.findByUsername(username).map(userDto -> userDto.getUser());
		
		Mono<List<Item>> shoppingCart = Flux.from(userDao.findByUsername(username))
				.map(userDto -> userDto.getShoppingCart())
				.flatMap(listUuids -> Flux.fromIterable(listUuids))
				.flatMap(uuid -> itemDao.findByUuid(uuid))
				.map(itemDto -> itemDto.getItem())
				.collectList();
		
		Mono<Tuple2<List<Item>,User>> bothThings = shoppingCart.zipWith(userMono);
		Mono<User> user = bothThings.map(tuple -> {
			User u = tuple.getT2();
			List<Item> item = tuple.getT1();
			u.setShoppingCart(item);
			return u;
		});
		
		Mono<List<Item>> wishList = Flux.from(userDao.findByUsername(username))
				.map(userDto -> userDto.getWishList())
				.flatMap(listUuids -> Flux.fromIterable(listUuids))
				.flatMap(uuid -> itemDao.findByUuid(uuid))
				.map(itemDto -> itemDto.getItem())
				.collectList();
		
		Mono<Tuple2<List<Item>,User>> both = wishList.zipWith(user);
		Mono<User> returnUser = both.map(tuple -> {
			User u2 = tuple.getT2();
			List<Item> item = tuple.getT1();
			u2.setWishList(item);
			return u2;
		});
				
		
		return returnUser;
	}
	
	@Override
	public Mono<User> register(String username, String firstName, String lastName, 
			String email, String address, Double currency) {
		
		User user = new User();
		user.setUsername(username);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setAddress(address);
		user.setCurrency(currency);
		user.setCurrentShop("No Store");
		user.setShoppingCart(new ArrayList<>());
		user.setWishList(new ArrayList<>());
		return userDao.save(new UserDTO(user)).map(u -> u.getUser());
	}
	
	@Override
	public Boolean checkAvailability(String newName) {
		// currently doesn't work
		// get rid of some of the warns at start up
		return true;
		
	}
	
	// Utility method used to save changes to the User database
	@Override
	public void updateUser(User user) {
		userDao.save(new UserDTO(user));
	}
	
	//As a User, I can add items to my ShoppingCart
	@Override
	public Mono<User> addToCart(String username, UUID itemId) {
		
		
		Mono<User> userMono = userDao.findByUsername(username).map(userDto -> userDto.getUser());
		Mono<Item> itemMono = itemDao.findByUuid(itemId).map(itemDto -> itemDto.getItem());
		
		Mono<List<Item>> shoppingCart = Flux.from(userDao.findByUsername(username))
				.map(userDto -> userDto.getShoppingCart())
				.flatMap(listUuids -> Flux.fromIterable(listUuids))
				.flatMap(uuid -> itemDao.findByUuid(uuid))
				.map(itemDto -> itemDto.getItem())
				.collectList();
		
		
		Mono<Tuple2<List<Item>,Item>> itemAndCart = shoppingCart.zipWith(itemMono);
		Mono<List<Item>> cart = itemAndCart.map(tuple -> {
			Item item = tuple.getT2();
			List<Item> items = tuple.getT1();
			items.add(item);
			return items;
		});
		
		Mono<Tuple2<List<Item>,User>> userAndCart = cart.zipWith(userMono);
		Mono<User> user = userAndCart.map(tuple -> {
			User u = tuple.getT2();
			List<Item> items = tuple.getT1();
			u.setShoppingCart(items);
			//userDao.save(new UserDTO(u));
			return u;
		});
		
		
		return user;
	}	
	
	@Override
	public Flux<Item> viewShoppingCart(String username){
		
		Flux<Item> shoppingCart = Flux.from(userDao.findByUsername(username))
				.map(userDto -> userDto.getShoppingCart())
				.flatMap(listUuids -> Flux.fromIterable(listUuids))
				.flatMap(uuid -> itemDao.findByUuid(uuid))
				.map(itemDto -> itemDto.getItem());
		return shoppingCart;
	}
	
	@Override
	public Flux<Item> viewWishList(String username) {
		Flux<Item> wishList = Flux.from(userDao.findByUsername(username))
				.map(userDto -> userDto.getWishList())
				.flatMap(listUuids -> Flux.fromIterable(listUuids))
				.flatMap(uuid -> itemDao.findByUuid(uuid))
				.map(itemDto -> itemDto.getItem());
		return wishList;
	}

}
