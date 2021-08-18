package com.revature.services;

import java.util.List;

import com.revature.beans.Item;

import reactor.core.publisher.Flux;

public interface StoreService {
	Flux<Item> listItems();
}
