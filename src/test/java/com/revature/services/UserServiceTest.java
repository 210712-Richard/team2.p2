package com.revature.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.revature.beans.User;
import com.revature.data.ReactiveItemDao;
import com.revature.data.ReactiveUserDao;

public class UserServiceTest {
	private static ReactiveUserDao userDao;
	private static ReactiveItemDao itemDao;
	private static UserServiceImpl service;
	private static User user;
	
	@BeforeAll
	public static void setUpClass() {
		user = new User();
	}
	
	@BeforeEach
	public void setUpTests() {
		userDao = Mockito.mock(ReactiveUserDao.class);
		itemDao = Mockito.mock(ReactiveItemDao.class);
		service = new UserServiceImpl(userDao, itemDao);
	}
	
	@Test
	public void testLogin() {
		
	}
	
	@Test
	public void testRegister() {
		
	}
	
	@Test
	public void updateUser() {
		
	}
	
	@Test
	public void testAddToCart() {
		
	}
	
	@Test
	public void testAddToWishlist() {
		
	}
	
	@Test
	public void testViewCart() {
		
	}
	
	@Test
	public void testViewWishlist() {
		
	}
	
	@Test
	public void testRemoveFromShoppingCart() {
		
	}
	
	@Test
	public void testRemoveFromWishlist() {
		
	}

}
