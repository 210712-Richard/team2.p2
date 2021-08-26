package com.revature.beans;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Item implements Serializable {
	// item UUID, item name (not necessarily unique), store item is from, price, category, images
	private UUID uuid;
	private String name;
	private String storename;
	private Double price;
	private ItemType category;
	private String picture;
	
	public Item() {
		super();
	}

	public Item(UUID uuid, String name, String storename, Double price, ItemType category) {
		this();
		this.uuid = uuid;
		this.name = name;
		this.storename = storename;
		this.price = price;
		this.category = category;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
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
		return Objects.hash(category, name, picture, price, storename, uuid);
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
		return category == other.category && Objects.equals(name, other.name) && Objects.equals(picture, other.picture)
				&& Objects.equals(price, other.price) && Objects.equals(storename, other.storename)
				&& Objects.equals(uuid, other.uuid);
	}

	@Override
	public String toString() {
		return "Item [uuid=" + uuid + ", name=" + name + ", storeName=" + storename + ", price=" + price + ", category="
				+ category + ", picture=" + picture + "]";
	}


	
	
}
