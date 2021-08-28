package com.revature.services;

import com.revature.beans.Item;
import com.revature.beans.ItemType;
import com.revature.dto.ItemDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ItemService {
	Flux<Item> getAllItems();
	
	Flux<ItemDTO> getItemsByPrice(Double price);
	
	Mono<Item> createItem(String name, String storename, Double price, ItemType category);

	Mono<Item> addCategoryToItem(Item item, String name);
}
