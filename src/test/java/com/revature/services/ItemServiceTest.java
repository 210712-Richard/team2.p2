package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.revature.beans.Item;
import com.revature.beans.ItemType;
import com.revature.data.ReactiveItemDao;
import com.revature.dto.ItemDTO;

import reactor.core.publisher.Mono;

public class ItemServiceTest {
	private static ReactiveItemDao itemDao;
	private static ItemServiceImpl service;
	private static Item item;
	
	@BeforeAll
	public static void setUpClass() {
		item = new Item();
		item.setUuid(UUID.fromString("6e2ab9c7-a2e1-4956-bca7-c439439d8dd6"));
		item.setStorename("Test Store");
		item.setName("Test Item");
		item.setPrice(20.0);
		item.setCategory(ItemType.PETS);
	}
	
	@BeforeEach
	public void setUpTests() {
		itemDao = Mockito.mock(ReactiveItemDao.class);
		service = new ItemServiceImpl(itemDao);
	}
	
	// create an item
	// String name, String storename, Double price, ItemType category
	@Test
	public void testCreate() {
		String storeName = "Test Store";
		String name = "Test Item";
		Double price = 20.0;
		ItemType category = ItemType.PETS;
		
		service.createItem(name, storeName, price, category);
		
		ArgumentCaptor<ItemDTO> captor = ArgumentCaptor.forClass(ItemDTO.class);
		Mockito.verify(service.itemDao).save(captor.capture());
		
		//Mono<Item> testItem =  captor.getValue().map(dto -> dto.getItem());
		//assertEquals(storeName, testItem.getStorename(), "Assert storename is the same");
		//assertEquals(name, testItem.getName(), "Assert name is the same");
		
	}
	
	@Test
	public void testGetAllItems() {
		
	}
	
	@Test
	public void testGetItemsByPrice() {
		
	}
	
	@Test
	public void testAddCategoryToItem() {
		
	}
	
	

}
