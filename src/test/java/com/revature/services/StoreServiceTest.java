package com.revature.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.revature.beans.Store;
import com.revature.data.ReactiveItemDao;
import com.revature.data.ReactiveStoreDao;

public class StoreServiceTest {
	private static ReactiveStoreDao storeDao;
	private static ReactiveItemDao itemDao;
	private static StoreServiceImpl service;
	private static Store store;
	
	@BeforeAll
	public static void setUpClass() {
		store = new Store();
	}
	
	@BeforeEach
	public void setUpTests() {
		storeDao = Mockito.mock(ReactiveStoreDao.class);
		itemDao = Mockito.mock(ReactiveItemDao.class);
		service = new StoreServiceImpl(itemDao, storeDao);
	}
	
	@Test
	public void testListItems() {
		
	}
	
	@Test
	public void testRegister() {
		
	}
	
	@Test
	public void testLogin() {
		
	}
	
	@Test
	public void testAddItemToInventory() {
		
	}

}
