package com.revature.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

import com.revature.beans.Item;
import com.revature.beans.User;
import com.revature.services.ItemService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/items")
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	//As a customer, I can get the list of all available items.
	@GetMapping("{username}")
	public ResponseEntity<Flux<Item>> getAllItems(@PathVariable("username") String name, WebSession session) {
		User loggedUser = (User) session.getAttribute("loggedUser");
		if(loggedUser == null) {
			return ResponseEntity.status(401).build();
		}
		if(!loggedUser.getUsername().equals(name)) {
			return ResponseEntity.status(403).build();
		}
				
		return ResponseEntity.ok(itemService.getAllItems());
	}
	
	//As a customer, I can get list of items sorted by price
	@GetMapping("{price}/{username}")
	public ResponseEntity<Flux<Item>> getItemsByPrice(@PathVariable("price") Double price, 
			@PathVariable("username") String name, WebSession session) {
		User loggedUser = (User) session.getAttribute("loggedUser");
		if(loggedUser == null) {
			return ResponseEntity.status(401).build();
		}
		if(!loggedUser.getUsername().equals(name)) {
			return ResponseEntity.status(403).build();
		}
		
		return ResponseEntity.ok(itemService.getItemsByPrice(price));	
	}
}
