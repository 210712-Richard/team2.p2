package com.revature.services;

import com.revature.beans.Item;
import com.revature.beans.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

	Mono<User> login(String username);

	Mono<User> register(String username, String firstName, String lastName, String email, String address,
			Double currency);

	Boolean checkAvailability(String newName);

	void updateUser(User user);

	Mono<Item> addToCart(Item item);

	Flux<Item> viewShoppingCart(String username);

	Flux<Item> viewWishList(String username);

}
