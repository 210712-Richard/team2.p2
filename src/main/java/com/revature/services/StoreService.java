package com.revature.services;

import java.util.UUID;

import com.revature.beans.Item;
import com.revature.beans.ItemType;
import com.revature.beans.Store;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StoreService {
	Flux<Item> listItems(String storeName);

	Mono<Item> createItem(String name, String storename, Double price, ItemType category);
	
	void deleteItem(Item item);

	Mono<Store> register(String name, String owner, Double currency);

	Mono<Store> login(String username);
	
	Boolean checkAvailability(String name);
	
	Mono<Store> addItemToInventory(String name, UUID id);
	
}
