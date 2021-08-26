package com.revature.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Item;
import com.revature.beans.ItemType;
import com.revature.data.ReactiveItemDao;
import com.revature.dto.ItemDTO;
import com.revature.dto.UserDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ItemServiceImpl implements ItemService {
	private ReactiveItemDao itemDao;
	
	@Autowired
	public ItemServiceImpl(ReactiveItemDao itemDao) {
		super();
		this.itemDao = itemDao;
	}
	
	@Override
	public Flux<Item> getAllItems() {
		return itemDao.findAll().map(dto -> dto.getItem());
	}

	@Override
	public List<ItemDTO> getItemsByPrice(Double price) {
		return itemDao.findAll().toStream().filter(dto -> dto.getPrice() < price).collect(Collectors.toList());
		}

	@Override
	public Mono<Item> createItem(UUID id, String name, String storename, Double price, ItemType category) {
		Item item = new Item();
		item.setUuid(id);
		item.setName(name);
		item.setStorename(storename);
		item.setPrice(price);
		item.setCategory(category);
		return itemDao.save(new ItemDTO(item)).map(i -> i.getItem());
	}
	
}
