package com.revature.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
	
	// username, email, address, userType, shoppingCart, currency, wishList, store (store name connects to store table)
	// when user makes account, chooses SELLER or CUSTOMER, if SELLER need header for storeName
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private Double currency;
	private String currentShop;
	private List<Item> shoppingCart;
	private List<Item> wishList;
	
	public User() {
		super();
		this.shoppingCart = new ArrayList<>();
		this.wishList = new ArrayList<>();
	}
	
	public User(String username, String firstName, String lastName, String email, String address, Double currency,
			String currentShop, List<Item> shoppingCart, List<Item> wishList) {
		this();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.currency = currency;
		this.currentShop = currentShop;
		this.shoppingCart = shoppingCart;
		this.wishList = wishList;
	}
	
	public User(String username, String firstName, String lastName, String email, String address, Double currency) {
		this();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.currency = currency;
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getCurrency() {
		return currency;
	}

	public void setCurrency(Double currency) {
		this.currency = currency;
	}
	
	public String getCurrentShop() {
		return currentShop;
	}

	public void setCurrentShop(String currentShop) {
		this.currentShop = currentShop;
	}

	public List<Item> getShoppingCart() {
		if(shoppingCart == null) {
			shoppingCart = new ArrayList<>();
		}
		return shoppingCart;
	}

	public void setShoppingCart(List<Item> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public List<Item> getWishList() {
		if(wishList == null) {
			wishList = new ArrayList<>();
		}
		return wishList;
	}

	public void setWishList(List<Item> wishList) {
		this.wishList = wishList;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((currentShop == null) ? 0 : currentShop.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((shoppingCart == null) ? 0 : shoppingCart.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((wishList == null) ? 0 : wishList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (currentShop == null) {
			if (other.currentShop != null)
				return false;
		} else if (!currentShop.equals(other.currentShop))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (shoppingCart == null) {
			if (other.shoppingCart != null)
				return false;
		} else if (!shoppingCart.equals(other.shoppingCart))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (wishList == null) {
			if (other.wishList != null)
				return false;
		} else if (!wishList.equals(other.wishList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", address=" + address + ", currency=" + currency + ", currentShop=" + currentShop + ", shoppingCart="
				+ shoppingCart + ", wishList=" + wishList + "]";
	}
	
	
	
	
	

}
