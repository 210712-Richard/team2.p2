package com.revature.controllers;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	// As a Store I can create an item
	@PostMapping("{name}/items")
	public ResponseEntity<Mono<Item>> createItem(@PathVariable("name") String name, @RequestBody Item item, WebSession session) {

		if (item == null) {
			return ResponseEntity.status(404).build();
		}
			
		Store loggedStore = (Store) session.getAttribute("loggedStore");
		// Check if store is not empty
		if (loggedStore == null) {
			return ResponseEntity.status(403).build();
		}
		//Create Item
		return ResponseEntity.ok(storeService.createItem(item.getName(), item.getStorename(), item.getPrice(), item.getCategory()));
	}

	// As a Store I can add my item to my inventory
	@PutMapping("{name}/inventory")
	public ResponseEntity<Mono<List<Item>>> addToInventory(@PathVariable("name") String name, @RequestBody Item item, WebSession session) {

		String loggedStore = (String) session.getAttribute("loggedStore");
		if (loggedStore == null) {
			return ResponseEntity.status(401).build();
		}
		if (!loggedStore.equals(name)) {
			return ResponseEntity.status(403).build();
		}

		return ResponseEntity.ok(storeService.addItemToInventory(name, (item.getUuid())).map(s -> s.getInventory()));
	}

	// Listing items by Store
	@GetMapping(value = "{storename}/items", produces = MediaType.APPLICATION_NDJSON_VALUE)
	public ResponseEntity<Flux<Item>> getItems(@PathVariable("storename") String storename) {
		return ResponseEntity.ok(storeService.listItems(storename).delayElements(Duration.ofSeconds(1)));
	}

	/*
	 * @DeleteMapping public Mono<ResponseEntity<Object>> deleteItem(@RequestBody
	 * Item item, WebSession session) { // Check for Seller authentication User
	 * loggedUser = (User) session.getAttribute("loggedUser"); if (loggedUser ==
	 * null || !UserType.SELLER.equals(loggedUser.getUserType())) { return
	 * Mono.just(ResponseEntity.status(403).build()); } // If Seller, then proceed
	 * to delete storeService.deleteItem(item); return
	 * Mono.just(ResponseEntity.status(201).build()); }
	 */

	// As a Seller I can login
	@PostMapping
	public ResponseEntity<Mono<Store>> login(@RequestBody Store store, WebSession session) {

		Mono<Store> loggedStore = storeService.login(store.getName());

		if (loggedStore == null) {
			return ResponseEntity.status(401).build();
		}
		if (session.getAttribute("loggedStore") == null) {
			session.getAttributes().put("loggedStore", store.getName());
		}

		// session.getAttributes().put("loggedStore", store.getName());
		return ResponseEntity.ok(loggedStore);
	}

	@DeleteMapping
	public ResponseEntity<Void> logout(WebSession session) {
		session.invalidate();
		return ResponseEntity.noContent().build();
	}

	// As a User I can create an account
	@PostMapping(value = "{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<Object>> register(@RequestBody Store store, @PathVariable("name") String name) {

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