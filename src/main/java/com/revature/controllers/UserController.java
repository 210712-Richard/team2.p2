package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.services.UserService;

import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PutMapping(value="{username}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> register(@RequestBody User user, @PathVariable("username") String name, @RequestHeader("UserType") String userType, 
			@RequestHeader("StoreName") String storeName ){
		
		// check if username is available
		if (userService.checkAvailability(name)) {
			
			// get userType and check if SELLER
			UserType type = UserType.valueOf(userType);
			if(UserType.SELLER.equals(type)){
				// userType is SELLER, check if storename is null
				if(storeName.isEmpty()) {
					return ResponseEntity.status(400).contentType(MediaType.TEXT_HTML).body("<p>Need store name</p>");
				}
			} else {
				type = UserType.CUSTOMER;
				storeName = null;
			}
			
			// call register method
			User created = userService.register(name, type, user.getFirstName(), user.getLastName(), 
					user.getEmail(), user.getAddress(), storeName);
			return ResponseEntity.ok(created);
			
		} else {
			// if availability returns false
			return ResponseEntity.status(400).contentType(MediaType.TEXT_HTML).body("<p>Username is taken</p>");
		}
		
	}
	
	
	
	/*
	@DeleteMapping
	LOGOUT(){
		
	}
	*/
	@PostMapping  // ("/users")
	public ResponseEntity<Mono<User>> login(@RequestBody User u, WebSession session){
		
		Mono<User> loggedUser = userService.login(u.getUsername());
		
		if(loggedUser == null) {
			return ResponseEntity.notFound().build();		
			}
		
		session.getAttributes().put("loggedUser", u);
		return ResponseEntity.ok(loggedUser);
	}
	
	
	
	

}
