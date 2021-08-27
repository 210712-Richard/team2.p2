package com.revature.controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

import com.revature.beans.Item;
import com.revature.beans.User;
import com.revature.dto.ItemDTO;
import com.revature.services.ItemService;
import com.revature.util.SessionFields;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/items")
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	//As a customer, I can get the list of all available items.
	@GetMapping(value = "{username}")
	public ResponseEntity<Flux<Item>> getAllItems(@PathVariable("username") String name, WebSession session) {
		User loggedUser = (User) session.getAttribute(SessionFields.LOGGED_USER);
		if(loggedUser == null) {
			return ResponseEntity.status(401).build();
		}
		if(!loggedUser.getUsername().equals(name)) {
			return ResponseEntity.status(403).build();
		}
				
		return ResponseEntity.ok(itemService.getAllItems());
	}
	
	//As a customer, I can get list of items sorted by price
	@GetMapping("{username}/searchItem")
	public ResponseEntity<Flux<ItemDTO>> getItemsByPrice(@RequestHeader(value="price") Double price, @PathVariable("username") String name, WebSession session) {		
		User loggedUser = (User) session.getAttribute(SessionFields.LOGGED_USER);
		if(loggedUser == null) {
			return ResponseEntity.status(401).build();
		}
		if(!loggedUser.getUsername().equals(name)) {
			return ResponseEntity.status(403).build();
		}
		
		return ResponseEntity.ok(itemService.getItemsByPrice(price));	
	}
}
