package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.data.ReactiveUserDao;
import com.revature.dto.UserDTO;

public class UserServiceImpl implements UserService {
	
	private ReactiveUserDao userDao;
	
	@Autowired
	public UserServiceImpl(ReactiveUserDao userDao) {
		super();
		this.userDao = userDao;
	}
	
	public User login() {
		return null;
	}
	
	public User register (String username, UserType userType, String firstName, String lastName, 
			String email, String address, String storeName) {
		User user = new User();
		user.setUsername(username);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setAddress(address);
		user.setUserType(userType);
		user.setCurrency(0d);
		if (UserType.SELLER.equals(userType)) {
			user.setStoreName(storeName);
		} else {
			user.setStoreName(null);
		}
		userDao.save(new UserDTO(user));
		return user;
	}

}
