package com.revature.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Item;
import com.revature.beans.Store;
import com.revature.data.ReactiveItemDao;
import com.revature.data.ReactiveStoreDao;
import com.revature.dto.ItemDTO;
import com.revature.dto.StoreDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StoreServiceImpl implements StoreService{
	private ReactiveItemDao itemDao;
	private ReactiveStoreDao storeDao;
	
	@Autowired
	public StoreServiceImpl(ReactiveItemDao itemDao) {
		super();
		this.itemDao = itemDao;
	}
	
	@Override
	public Flux<Item> listItems(String storeName) {
		return itemDao.findByStorename(storeName).map(dto -> dto.getItem());
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

	@Override
	public Mono<Store> register(String name, String owner, Double currency) {
		Store store = new Store();
		store.setName(name);
		store.setOwner(owner);
		store.setCurrency(currency);
		store.setInventory(new ArrayList<>());
		return storeDao.save(new StoreDTO(store)).map(s -> s.getStore());
	}
	
	
}
