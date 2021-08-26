package com.revature.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.revature.beans.Store;

@Table("store")
public class StoreDTO {
	@PrimaryKeyColumn(
			name="name",
			ordinal=0,
			type=PrimaryKeyType.PARTITIONED
			)
	private String name;
	@Column
	private List<UUID> inventory;
	@Column
	private String owner;
	@Column
	private Double currency;
	
	public StoreDTO() {
		
	}
	
	public StoreDTO(Store store) {
		this.name = store.getName();
		this.inventory = new ArrayList<>();
		store.getInventory().stream().forEach(item-> this.inventory.add(item.getUuid()));
		this.owner = store.getOwner();
		this.currency = store.getCurrency();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UUID> getInventory() {
		return inventory;
	}

	public void setInventory(List<UUID> inventory) {
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
		StoreDTO other = (StoreDTO) obj;
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
		return "StoreDTO [name=" + name + ", inventory=" + inventory + ", owner=" + owner + ", currency=" + currency
				+ "]";
	}

	
	public Store getStore() {
		Store store = new Store();
		store.setName(this.name);
		store.setOwner(this.owner);
		// get by this.name And uuid provided
		// for loop of size of list?
		store.setInventory(new ArrayList<>());
		store.setCurrency(this.currency);
		return store;
	}
	
	

}
