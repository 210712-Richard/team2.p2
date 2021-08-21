package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	// test connection to localhost
	@GetMapping("/hello")
	public ResponseEntity<String> hello(){
		return ResponseEntity.status(200).contentType(MediaType.TEXT_HTML).body("<p>Hello</p>");
	}
	
	@PostMapping(value="{username}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> register(@RequestBody User user, @PathVariable("username") String name){
		
		// check if username is available
		if (userService.checkAvailability(name)) {
			// get userType and check if SELLER
			if(UserType.SELLER.equals(user.getUserType())){
				// userType is SELLER, check if storename is null
				if(user.getStoreName().isEmpty()) {
					return ResponseEntity.status(400).contentType(MediaType.TEXT_HTML).body("<p>Need store name</p>");
				}
			} else {
				user.setUserType(UserType.CUSTOMER);
				user.setStoreName("NoStore");
			}
			
			// call register method
			User created = userService.register(name, user.getUserType(), user.getFirstName(), user.getLastName(), 
					user.getEmail(), user.getAddress(), user.getStoreName());
			return ResponseEntity.ok(created);
			
		} else {
			// if availability returns false
			return ResponseEntity.status(400).contentType(MediaType.TEXT_HTML).body("<p>Username is taken</p>");
		}
		
	}
	
	
	
	
	@DeleteMapping
	public ResponseEntity<Void> logout(WebSession session){
		session.invalidate();
		return ResponseEntity.noContent().build();
	}
	
	
	
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
