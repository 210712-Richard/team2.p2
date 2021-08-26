package com.revature.services;

import java.util.ArrayList;
import java.util.List;

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
import reactor.util.function.Tuple2;

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

	@Override
	public Boolean checkAvailability(String name) {
		return true;
	}
	
	@Override
	public Mono<Store> login(String username){
		Mono<Store> storeMono = storeDao.findByName(username).map(storeDto -> storeDto.getStore());
		
		Mono<List<Item>> inventory = Flux.from(storeDao.findByName(username))
				.map(storeDto -> storeDto.getInventory())
				.flatMap(listUuids -> Flux.fromIterable(listUuids))
				.flatMap(uuid -> itemDao.findByUuid(uuid))
				.map(itemDto -> itemDto.getItem())
				.collectList();
		
		Mono<Tuple2<List<Item>,Store>> itemsAndStore = inventory.zipWith(storeMono);
		Mono<Store> returnStore = itemsAndStore.map(tuple -> {
			Store store = tuple.getT2();
			List<Item> items = tuple.getT1();
			store.setInventory(items);
			return store;
		});
		
		return returnStore;
	}
}
