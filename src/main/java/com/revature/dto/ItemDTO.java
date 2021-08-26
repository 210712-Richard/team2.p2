package com.revature.dto;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.revature.beans.Item;
import com.revature.beans.ItemType;

@Table("item")
public class ItemDTO {
	
	@PrimaryKeyColumn (
			name="uuid",
			ordinal=1,
			type=PrimaryKeyType.CLUSTERED
			)
	private UUID uuid;
	private String name;
	@PrimaryKeyColumn (
			name="storeName",
			ordinal=0,
			type=PrimaryKeyType.PARTITIONED
			)
	private String storename;
	private Double price;
	private ItemType category;
	private String picture;
	
	public ItemDTO() {
		
	}
	
	public ItemDTO(Item item) {
		this.uuid = item.getUuid();
		this.name = item.getName();
		this.storename = item.getStorename();
		this.price = item.getPrice();
		this.category = item.getCategory();
		this.picture = item.getPicture();
	}
	
	public Item getItem() {
		Item i = new Item();
		i.setUuid(this.uuid);
		i.setName(this.name);
		i.setStorename(this.storename);
		i.setPrice(this.price);
		i.setCategory(this.category);
		i.setPicture(this.picture);
		return i;
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((storename == null) ? 0 : storename.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		ItemDTO other = (ItemDTO) obj;
		if (category != other.category)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (picture == null) {
			if (other.picture != null)
				return false;
		} else if (!picture.equals(other.picture))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (storename == null) {
			if (other.storename != null)
				return false;
		} else if (!storename.equals(other.storename))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemDTO [uuid=" + uuid + ", name=" + name + ", storename=" + storename + ", price=" + price
				+ ", category=" + category + ", picture=" + picture + "]";
	}
	
}
