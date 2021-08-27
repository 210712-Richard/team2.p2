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
	public UserServiceImpl(ReactiveUserDao userDao, ReactiveItemDao itemDao) {
		super();
		this.userDao = userDao;
		this.itemDao = itemDao;
	}

	@Override
	public Mono<User> login(String username) {

		Mono<User> userMono = userDao.findByUsername(username).map(userDto -> userDto.getUser());

		Mono<List<Item>> shoppingCart = Flux.from(userDao.findByUsername(username))
				.map(userDto -> userDto.getShoppingCart()).flatMap(listUuids -> Flux.fromIterable(listUuids))
				.flatMap(uuid -> itemDao.findByUuid(uuid)).map(itemDto -> itemDto.getItem()).collectList();

		Mono<Tuple2<List<Item>, User>> bothThings = shoppingCart.zipWith(userMono);
		Mono<User> user = bothThings.map(tuple -> {
			User u = tuple.getT2();
			List<Item> item = tuple.getT1();
			u.setShoppingCart(item);
			return u;
		});

		Mono<List<Item>> wishList = Flux.from(userDao.findByUsername(username)).map(userDto -> userDto.getWishList())
				.flatMap(listUuids -> Flux.fromIterable(listUuids)).flatMap(uuid -> itemDao.findByUuid(uuid))
				.map(itemDto -> itemDto.getItem()).collectList();

		Mono<Tuple2<List<Item>, User>> both = wishList.zipWith(user);
		Mono<User> returnUser = both.map(tuple -> {
			User u2 = tuple.getT2();
			List<Item> item = tuple.getT1();
			u2.setWishList(item);
			return u2;
		});

		return returnUser;
	}

	@Override
	public Mono<User> register(String username, String firstName, String lastName, String email, String address,
			Double currency) {
		
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
		return true;

	}

	// Utility method used to save changes to the User database
	@Override
	public void updateUser(User user) {
		userDao.save(new UserDTO(user));
	}

	// As a User, I can add items to my ShoppingCart
	@Override
	public Mono<User> addToCart(String username, UUID itemId) {

		Mono<User> userMono =  userDao.findByUsername(username).flatMap(user -> {
			List<UUID> newList = new ArrayList<UUID>(user.getShoppingCart());
			newList.add(itemId);
			user.setShoppingCart(newList);
			return userDao.save(user);
		}).map(user -> user.getUser());
		
		// get user's shoppingCart
		Mono<List<Item>> wishList = Flux.from(userDao.findByUsername(username))
				.map(userDto -> userDto.getShoppingCart())
				.flatMap(listUuids -> Flux.fromIterable(listUuids))
				.flatMap(uuid -> itemDao.findByUuid(uuid))
				.map(itemDto -> itemDto.getItem())
				.collectList();
		
		return wishList.zipWith(userMono)
				.map(tup -> {
					User u = tup.getT2();
					u.setWishList(tup.getT1());
					return u;
				});
	}

	@Override
	public Flux<Item> viewShoppingCart(String username) {

		Flux<Item> shoppingCart = Flux.from(userDao.findByUsername(username)).map(userDto -> userDto.getShoppingCart())
				.flatMap(listUuids -> Flux.fromIterable(listUuids)).flatMap(uuid -> itemDao.findByUuid(uuid))
				.map(itemDto -> itemDto.getItem());
		return shoppingCart;
	}

	@Override
	public Flux<Item> viewWishList(String username) {
		Flux<Item> wishList = Flux.from(userDao.findByUsername(username)).map(userDto -> userDto.getWishList())
				.flatMap(listUuids -> Flux.fromIterable(listUuids)).flatMap(uuid -> itemDao.findByUuid(uuid))
				.map(itemDto -> itemDto.getItem());
		return wishList;
	}

	@Override
	public Mono<User> addToWishlist(String username, UUID itemId) {

		// get user and update wishlist
		Mono<User> userMono =  userDao.findByUsername(username).flatMap(user -> {
			List<UUID> newList = new ArrayList<UUID>(user.getWishList());
			newList.add(itemId);
			user.setWishList(newList);
			return userDao.save(user);
		}).map(user -> user.getUser());
		
		// get list
		Mono<List<Item>> wishList = Flux.from(userDao.findByUsername(username))
				.map(userDto -> userDto.getWishList())
				.flatMap(listUuids -> Flux.fromIterable(listUuids))
				.flatMap(uuid -> itemDao.findByUuid(uuid))
				.map(itemDto -> itemDto.getItem())
				.collectList();
		
		// return new list
		return wishList.zipWith(userMono)
				.map(tup -> {
					User u = tup.getT2();
					u.setWishList(tup.getT1());
					return u;
				});
	}

	@Override
	public Mono<User> removeFromShoppingCart(String username, UUID itemId) {
		// get user and update shoppingcart
		Mono<User> userMono =  userDao.findByUsername(username).flatMap(user -> {
			List<UUID> cart = new ArrayList<UUID>(user.getShoppingCart());
			// remove will remove first that matches
			cart.remove(itemId);
			user.setShoppingCart(cart);
			return userDao.save(user);
		}).map(user -> user.getUser());
		
		// get list
		Mono<List<Item>> shoppingCart = Flux.from(userDao.findByUsername(username))
				.map(userDto -> userDto.getShoppingCart())
				.flatMap(listUuids -> Flux.fromIterable(listUuids))
				.flatMap(uuid -> itemDao.findByUuid(uuid))
				.map(itemDto -> itemDto.getItem())
				.collectList();
		
		// return list
		return shoppingCart.zipWith(userMono)
				.map(tup -> {
					User user = tup.getT2();
					user.setShoppingCart(tup.getT1());
					return user;
				});
	}
	
	//As a user, I can remove items from my wishlist
//	@Override
//	public Mono<User> removeFromWishlist(String username, UUID itemId) {		
//		return userDao.findByUsername(username).flatMap(dto -> {
//			List<UUID> list = dto.getWishList().stream().collect(Collectors.toList());
//			list.removeIf(i -> i.equals(itemId));
//			dto.setWishList(list);
//			return userDao.save(dto);
//		}).map(i -> i.getUser());
//	}
	@Override
	public Mono<User> removeFromWishlist(String username, UUID itemId) {
		//get user and update wishlist
		Mono<User> userMono =  userDao.findByUsername(username).flatMap(user -> {
			List<UUID> wishlist = new ArrayList<UUID>(user.getWishList());
			//get index of item and remove it
			wishlist.remove(itemId);
			user.setWishList(wishlist);
			return userDao.save(user);
		}).map(user -> user.getUser());
		
		//get List
		Mono<List<Item>> wishlist = Flux.from(userDao.findByUsername(username))
				.map(userDto -> userDto.getWishList())
				.flatMap(listUuids -> Flux.fromIterable(listUuids))
				.flatMap(uuid -> itemDao.findByUuid(uuid))
				.map(itemDto -> itemDto.getItem())
				.collectList();
		
		//return list
		return wishlist.zipWith(userMono)
				.map(tup -> {
					User user = tup.getT2();
					user.setWishList(tup.getT1());
					return user;
				});
	}
	
}
