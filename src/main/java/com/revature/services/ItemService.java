package com.revature.services;

import com.revature.beans.Item;

import reactor.core.publisher.Flux;

public interface ItemService {
	Flux<Item> getAllItems();
	
	Flux<Item> getItemsByPrice(Double price);
}
