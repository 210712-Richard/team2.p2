package com.revature.beans;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import com.datastax.oss.driver.api.core.data.TupleValue;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.core.type.TupleType;

public class Item implements Serializable {
	// item UUID, item name (not necessarily unique), store item is from, price, category, images
	private UUID id;
	private String name;
	private String storeName;
	private Double price;
	private ItemType category;
	private String picture;
	
	public Item() {
		super();
	}

	public Item(UUID id, String name, String storeName, Double price, ItemType category) {
		this();
		this.id = id;
		this.name = name;
		this.storeName = storeName;
		this.price = price;
		this.category = category;
	}

	public UUID getId() {
		return id;
	}

	public void setUuid(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ItemType getCategory() {
		return category;
	}

	public void setCategory(ItemType category) {
		this.category = category;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, id, name, picture, price, storeName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return category == other.category && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(picture, other.picture) && Objects.equals(price, other.price)
				&& Objects.equals(storeName, other.storeName);
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", storeName=" + storeName + ", price=" + price + ", category="
				+ category + ", picture=" + picture + "]";
	}


	
	
}
