package com.revature.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Store implements Serializable {
	// storeName (unique), inventory (list of items), user/owner's name
	private String name;
	private List<Item> inventory;
	private String owner;
	private Double currency;
	
	public Store() {
		super();
		this.inventory = new ArrayList<>();
	}
	
	public Store(String name, List<Item> inventory, String owner, Double currency) {
		this();
		this.name = name;
		this.owner = owner;
		this.currency = currency;
		this.inventory = inventory;
	}
	
	public Store(String name, String owner, Double currency) {
		this();
		this.name = name;
		this.owner = owner;
		this.currency = currency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Item> getInventory() {
		return inventory;
	}

	public void setInventory(List<Item> inventory) {
		if(inventory ==  null) {
			inventory = new ArrayList<>();
		}
		this.inventory = inventory;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Double getCurrency() {
		return currency;
	}

	public void setCurrency(Double currency) {
		this.currency = currency;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((inventory == null) ? 0 : inventory.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
		Store other = (Store) obj;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (inventory == null) {
			if (other.inventory != null)
				return false;
		} else if (!inventory.equals(other.inventory))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Store [name=" + name + ", inventory=" + inventory + ", owner=" + owner + ", currency=" + currency + "]";
	}
	
	
	

}
