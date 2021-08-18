package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.beans.User;
import com.revature.data.ReactiveUserDao;

public class UserServiceImpl implements UserService {
	
	private ReactiveUserDao userDao;
	
	@Autowired
	public UserServiceImpl(ReactiveUserDao userDao) {
		super();
		this.userDao = userDao;
	}
	
	public User login() {
		
	}
	
	public User register () {
		
	}

}
