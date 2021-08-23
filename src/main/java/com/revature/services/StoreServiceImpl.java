package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


import com.revature.beans.Item;
import com.revature.data.ReactiveItemDao;
import com.revature.dto.ItemDTO;

@Service
public class StoreServiceImpl implements StoreService{
	private ReactiveItemDao itemDao;
	
	@Autowired
	public StoreServiceImpl(ReactiveItemDao itemDao) {
		super();
		this.itemDao = itemDao;
	}
	
	@Override
	public Flux<Item> listItems(String storeName) {
		return itemDao.findByStoreName(storeName).map(dto -> dto.getItem());
	}

	@Override
	public Item createItem(Item item) {
		itemDao.save(new ItemDTO(item));
		return item;
	}

	@Override
	public void deleteItem(Item item) {
		itemDao.delete(new ItemDTO(item));
	}
}
