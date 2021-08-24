package com.revature.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.revature.beans.User;
import com.revature.beans.UserType;

@Table("user")
public class UserDTO {
	@PrimaryKeyColumn(
			name="username",
			ordinal=0,
			type=PrimaryKeyType.PARTITIONED
			)
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private Double currency;
	private UserType userType;
	private String currentShop;
	@Column("shoppingCart")
	private List<UUID> shoppingCart;
	@Column("wishList")
	private List<UUID> wishList;
	private String storeName;
	
	public UserDTO() {
		
	}
	
	public UserDTO(User user) {
		this.username = user.getUsername();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.address = user.getAddress();
		this.currency = user.getCurrency();
		this.userType = user.getUserType();
		this.currentShop = user.getCurrentShop();
		this.shoppingCart = new ArrayList<>();
		user.getShoppingCart().stream().forEach(item -> this.shoppingCart.add(item.getUuid()));
		this.wishList = new ArrayList<>();
		user.getWishList().stream().forEach(item-> this.wishList.add(item.getUuid()));
		this.storeName = user.getStoreName();
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getCurrentShop() {
		return currentShop;
	}

	public void setCurrentShop(String currentShop) {
		this.currentShop = currentShop;
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

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public List<UUID> getShoppingCart() {
		if(shoppingCart == null) {
			shoppingCart = new ArrayList<>();
		}
		return shoppingCart;
	}

	public void setShoppingCart(List<UUID> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public List<UUID> getWishList() {
		if(wishList == null) {
			wishList = new ArrayList<>();
		}
		return wishList;
	}

	public void setWishList(List<UUID> wishList) {
		this.wishList = wishList;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	public User getUser() {
		User u = new User(this.username, this.firstName, this.lastName, this.email, 
				this.address, this.currency, this.userType, this.storeName );
		u.setUserType(this.userType);
		return u;
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
		result = prime * result + ((storeName == null) ? 0 : storeName.hashCode());
		result = prime * result + ((userType == null) ? 0 : userType.hashCode());
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
		UserDTO other = (UserDTO) obj;
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
		if (storeName == null) {
			if (other.storeName != null)
				return false;
		} else if (!storeName.equals(other.storeName))
			return false;
		if (userType != other.userType)
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
		return "UserDTO [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", address=" + address + ", currency=" + currency + ", userType=" + userType
				+ ", currentShop=" + currentShop + ", shoppingCart=" + shoppingCart + ", wishList=" + wishList
				+ ", storeName=" + storeName + "]";
	}
	

}
