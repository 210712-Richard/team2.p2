package com.revature.services;

import java.util.List;
import java.util.UUID;

import com.revature.beans.Item;
import com.revature.beans.ItemType;
import com.revature.dto.ItemDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ItemService {
	Flux<Item> getAllItems();
	
	Flux<ItemDTO> getItemsByPrice(Double price);
	
	Mono<Item> createItem(UUID id, String name, String storename, Double price, ItemType category);
}
