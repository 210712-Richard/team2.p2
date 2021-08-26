package com.revature.controllers;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

import com.revature.beans.Item;
import com.revature.beans.Store;
import com.revature.services.ItemService;
import com.revature.services.StoreService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/stores")
public class StoreController {
	@Autowired
	private StoreService storeService;
	@Autowired
	private ItemService itemService;

	/*
	// As a Seller I can create an item
	@PostMapping
	public Mono<ResponseEntity<Object>> createItem(@RequestBody Item item, WebSession session) {

		if(item==null)
			return Mono.just(ResponseEntity.status(404).build());
		User loggedUser = (User) session.getAttribute(SessionFields.LOGGED_USER);
		System.out.print(loggedUser.toString());
		// Check if user is not empty or user is not a CUSTOMER
		if (loggedUser == null || !UserType.SELLER.equals(loggedUser.getUserType())) {
			return Mono.just(ResponseEntity.status(403).build());
		}
		// If Seller, then proceed
		System.out.println(item.toString());
		return Mono.just(itemService.createItem(UUID.randomUUID(), item.getName(), item.getStorename(), item.getPrice(),
				item.getCategory())).map(i -> {
					if (i == null) {
						return ResponseEntity.status(409).build();
					} else {
						return ResponseEntity.ok(i);
					}
				});
	}
	*/

	// Listing items by Store
	@GetMapping(value = "{storeName}/items", produces = MediaType.APPLICATION_NDJSON_VALUE)
	public ResponseEntity<Flux<Item>> getItems(@PathVariable("storeName") String storeName) {
		return ResponseEntity.ok(storeService.listItems(storeName).delayElements(Duration.ofSeconds(1)));
	}

	/*
	@DeleteMapping
	public Mono<ResponseEntity<Object>> deleteItem(@RequestBody Item item, WebSession session) {
		// Check for Seller authentication
		User loggedUser = (User) session.getAttribute("loggedUser");
		if (loggedUser == null || !UserType.SELLER.equals(loggedUser.getUserType())) {
			return Mono.just(ResponseEntity.status(403).build());
		}
		// If Seller, then proceed to delete
		storeService.deleteItem(item);
		return Mono.just(ResponseEntity.status(201).build());
	}
	*/
	
	// As a Seller I can login
	@PostMapping
	public ResponseEntity<Mono<Store>> login(@RequestBody Store store, WebSession session){
		
		Mono<Store> loggedStore = storeService.login(store.getName());
		
		if(loggedStore == null) {
			return ResponseEntity.status(401).build();
			}
		
		session.getAttributes().put("loggedStore", store);
		return ResponseEntity.ok(loggedStore);
	}
	
	// As a User I can create an account
	@PostMapping(value="{name}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<Object>> register(@RequestBody Store store, @PathVariable("name") String name){
		
		// check if name is available
		if (Boolean.TRUE.equals(storeService.checkAvailability(name))) {
			// register store
			return storeService.register(name, store.getOwner(), store.getCurrency()).map(u -> ResponseEntity.ok(u));
		} else {
			// if availability returns false
			return Mono.just(ResponseEntity.status(400).contentType(MediaType.TEXT_HTML).build());
		}
		
	}
	
}