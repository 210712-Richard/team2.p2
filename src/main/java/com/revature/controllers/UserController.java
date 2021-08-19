package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.revature.beans.User;
import com.revature.services.UserService;
import com.sun.tools.sjavac.Log;

import reactor.core.publisher.Mono;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	/*
	@PutMapping
	register() {
		
	}
	*/
	
	
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
