package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Item;
import com.revature.data.ReactiveItemDao;
import com.revature.dto.ItemDTO;

import reactor.core.publisher.Flux;

@Service
public class StoreServiceImpl implements StoreService{

	//gachaDao.findAll().stream().map(dto -> dto.getHistoricalCat()).collect(Collectors.toList());
	//private static Logger log = LogManager.getLogger(StoreServiceImpl.class);
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
}
