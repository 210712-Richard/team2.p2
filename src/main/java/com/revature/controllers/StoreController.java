package com.revature.controllers;

import java.time.Duration;

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
import com.revature.beans.UserType;
import com.revature.services.StoreService;
import com.revature.services.StoreServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/store")
public class StoreController {

	private StoreService storeService;

	// As a Seller I can create an item
	@PostMapping
	public Mono<ResponseEntity<Object>> createItem(@RequestBody Item item, WebSession session) {

		// Check for Seller authentication
		User loggedUser = (User) session.getAttribute("loggedUser");
		if (loggedUser == null || !UserType.SELLER.equals(loggedUser.getUserType())) {
			return Mono.just(ResponseEntity.status(403).build());
		}
		// If Seller, then proceed
		return Mono.just(storeService.createItem(item)).map((i) -> {
			if (i == null) {
				return ResponseEntity.status(409).build();
			} else {
				return ResponseEntity.ok(i);
			}
		});
	}

	// Listing items by Store
	@GetMapping(value = "{storeName}/items", produces = MediaType.APPLICATION_NDJSON_VALUE)
	public ResponseEntity<Flux<Item>> getItems(@PathVariable("storeName") String storeName) {
		return ResponseEntity.ok(storeService.listItems(storeName).delayElements(Duration.ofSeconds(1)));
	}

	@DeleteMapping
	public Mono<ResponseEntity<Object>> deleteItem(@RequestBody Item item, WebSession session) {
		// Check for Seller authentication
		User loggedUser = (User) session.getAttribute("loggedUser");
		if (loggedUser == null || !UserType.SELLER.equals(loggedUser.getUserType())) {
			return Mono.just(ResponseEntity.status(403).build());
		}
		//If Seller, then proceed to delete
		storeService.deleteItem(item);
		return Mono.just(ResponseEntity.status(201).build());
	}
}