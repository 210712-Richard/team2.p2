package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.revature.beans.Item;
import com.revature.data.ReactiveItemDao;

import reactor.core.publisher.Flux;

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
	public Flux<Item> getItemsByPrice(Double price) {
		return itemDao.findByPrice(price).map(dto -> dto.getItem());
	}
	
}
