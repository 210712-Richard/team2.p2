package com.revature.services;

import com.revature.beans.User;
import com.revature.beans.UserType;

import reactor.core.publisher.Mono;

public interface UserService {

	Mono<User> login(String name);

	User register(String username, UserType userType, String firstName, String lastName, String email, String address,
			String storeName);

}
