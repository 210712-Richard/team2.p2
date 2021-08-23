package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Item;
import com.revature.beans.User;
import com.revature.data.ReactiveItemDao;
import com.revature.beans.UserType;
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
		
		Mono<User> userMono = userDao.findById(username).map(user -> user.getUser());
		
		Mono<List<Item>> shoppingCart = Flux.from(userDao.findByUsername(username))
				.map(user -> user.getShoppingCart())
				.flatMap(list -> Flux.fromIterable(list))
				.flatMap(id -> itemDao.findById(id))
				.map(item -> item.getItem())
				.collectList();
		Mono<Tuple2<List<Item>,User>> bothThings = shoppingCart.zipWith(userMono);
		Mono<User> user = bothThings.map(tuple -> {
			User u = tuple.getT2();
			List<Item> item = tuple.getT1();
			u.setShoppingCart(item);
			return u;
		});
		
		Mono<List<Item>> wishList = Flux.from(userDao.findByUsername(username))
				.map(user2 -> user2.getWishList())
				.flatMap(list -> Flux.fromIterable(list))
				.flatMap(id -> itemDao.findById(id))
				.map(item -> item.getItem())
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
	public User register(String username, UserType userType, String firstName, String lastName, 
			String email, String address, String storeName) {
		
		User user = new User();
		user.setUsername(username);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setAddress(address);
		user.setUserType(userType);
		user.setCurrency(0d);
		user.setStoreName(storeName);
		user.setShoppingCart(new ArrayList<Item>());
		user.setWishList(new ArrayList<Item>());
		userDao.save(new UserDTO(user));
		return user;
	}
	
	@Override
	public Boolean checkAvailability(String newName) {
		return true;
		
	}
	
	// Utility method used to save changes to the User database
	@Override
	public void updateUser(User user) {
		userDao.save(new UserDTO(user));
	}
	
	//As a User, I can add items to my ShoppingCart
	@Override
	public Item addToCart(Item item) {
		return null;
	}
	

}
