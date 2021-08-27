package com.revature.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

import com.revature.beans.Item;
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
		String loggedUser = (String) session.getAttribute(SessionFields.LOGGED_USER);
		if(loggedUser == null) {
			return ResponseEntity.status(401).build();
		}
		if(!loggedUser.equals(name)) {
			return ResponseEntity.status(403).build();
		}
				
		return ResponseEntity.ok(itemService.getAllItems());
	}
	
	//As a customer, I can get list of items sorted by price
	@GetMapping("{username}/price")
	public ResponseEntity<Flux<ItemDTO>> getItemsByPrice(@RequestHeader(value="price") Double price, @PathVariable("username") String name, WebSession session) {		
		String loggedUser = (String) session.getAttribute(SessionFields.LOGGED_USER);
		if(loggedUser == null) {
			return ResponseEntity.status(401).build();
		}
		if(!loggedUser.equals(name)) {
			return ResponseEntity.status(403).build();
		}
		
		return ResponseEntity.ok(itemService.getItemsByPrice(price));	
	}
	

	// add category tags to an item
	@PostMapping("{name}/tags")
	public ResponseEntity<Mono<Item>> addCategoryToItem(@RequestBody Item item, @PathVariable("name") String name, WebSession session){
		String loggedUser = (String) session.getAttribute("loggedUser");
		if(loggedUser == null) {
			return ResponseEntity.status(401).build();
		}
		if(!loggedUser.equals(name)) {
			return ResponseEntity.status(403).build();
		}
		
		return ResponseEntity.ok(itemService.addCategoryToItem(item, name));
		
	}
	
}
