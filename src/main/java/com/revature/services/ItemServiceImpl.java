package com.revature.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Item;
import com.revature.beans.ItemType;
import com.revature.data.ReactiveItemDao;
import com.revature.data.ReactiveStoreDao;
import com.revature.dto.ItemDTO;

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
	public Flux<ItemDTO> getItemsByPrice(Double price) {
		return itemDao.findAll().filter(dto -> dto.getPrice() <= price);
	}

	@Override
	public Mono<Item> createItem(String name, String storename, Double price, ItemType category) {
		Item item = new Item();
		item.setUuid(UUID.randomUUID());
		item.setName(name);
		item.setStorename(storename);
		item.setPrice(price);
		item.setCategory(category);
		return itemDao.save(new ItemDTO(item)).map(i -> i.getItem());
	}

	@Override
	public Mono<Item> addCategoryToItem(Item item, String name) {
		// get item and set item category
		return itemDao.findByStorenameAndUuid(name, item.getUuid()).flatMap(itemEdit -> {
			itemEdit.setCategory(item.getCategory());
			return itemDao.save(itemEdit);
		}).map(itemEdit -> itemEdit.getItem());
		
	}
	
}
