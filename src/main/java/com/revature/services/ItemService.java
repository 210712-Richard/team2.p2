package com.revature.services;

import java.util.List;

import com.revature.beans.Item;
import com.revature.dto.ItemDTO;

import reactor.core.publisher.Flux;

public interface ItemService {
	Flux<Item> getAllItems();
	
	List<ItemDTO> getItemsByPrice(Double price);
	
}
