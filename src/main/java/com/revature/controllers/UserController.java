package com.revature.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

import com.revature.beans.Item;
import com.revature.beans.User;
import com.revature.services.ItemService;
import com.revature.services.StoreService;
import com.revature.services.UserService;
import com.revature.util.SessionFields;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ItemService itemService;
	
	// test connection to localhost
	@GetMapping("/hello")
	public ResponseEntity<String> hello(){
		return ResponseEntity.status(200).contentType(MediaType.TEXT_HTML).body("<p>Hello</p>");
	}
	
	// As a User I can create an account
	@PostMapping(value="{username}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<Object>> register(@RequestBody User user, @PathVariable("username") String name){
		
		// check if username is available
		if (Boolean.TRUE.equals(userService.checkAvailability(name))) {
			// register customer
			return userService.register(name, user.getFirstName(), user.getLastName(),
					user.getEmail(), user.getAddress(), user.getCurrency()).map(u -> ResponseEntity.ok(u));
		} else {
			// if availability returns false
			return Mono.just(ResponseEntity.status(400).contentType(MediaType.TEXT_HTML).build());
		}
		
	}
	
	
	
	
	@DeleteMapping
	public ResponseEntity<Void> logout(WebSession session){
		session.invalidate();
		return ResponseEntity.noContent().build();
	}
	
	// As a User I can login
	@PostMapping
	public ResponseEntity<Mono<User>> login(@RequestBody User u, WebSession session){
		
		Mono<User> loggedUser = userService.login(u.getUsername());
		
		if(loggedUser == null) {
			return ResponseEntity.status(401).build();
			}
		
		session.getAttributes().put("loggedUser", u);
		return ResponseEntity.ok(loggedUser);
	}
	
	
	// As a User I can add items to my ShoppingCart
	@PostMapping("{username}/shoppingCart")
	public ResponseEntity<Flux<Item>> addToCart(@RequestBody UUID itemId, @PathVariable("username") String username, WebSession session){
		
		User loggedUser = (User) session.getAttribute("loggedUser");
		if(loggedUser == null) {
			return ResponseEntity.status(401).build();
		}
		if(!loggedUser.getUsername().equals(username)) {
			return ResponseEntity.status(403).build();
		}
		
		Mono<User> user = userService.addToCart(username, itemId);
		
		//userService.updateUser(user);
		
		return ResponseEntity.ok(userService.viewShoppingCart(username));
	}
	
	
	
	

}
