package com.revature.services;

import com.revature.beans.Item;
import com.revature.beans.User;
import com.revature.beans.UserType;

import reactor.core.publisher.Mono;

public interface UserService {

	Mono<User> login(String username);

	Mono<User> register(String username, UserType userType, String firstName, String lastName, String email, String address,
			String storeName);

	Boolean checkAvailability(String newName);

	void updateUser(User user);

	Item addToCart(Item item);

}
