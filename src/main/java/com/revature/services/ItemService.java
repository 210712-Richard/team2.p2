package com.revature.services;

import com.revature.beans.Item;

import reactor.core.publisher.Flux;

public interface ItemService {
	public Flux<Item> getAllItems();
}
