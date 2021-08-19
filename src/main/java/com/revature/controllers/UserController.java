package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.revature.services.UserService;

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
	
	@PostMapping
	LOGIN(){
		
	}
	*/
	
	
	

}
