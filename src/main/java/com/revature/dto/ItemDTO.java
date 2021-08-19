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
			name="id",
			ordinal=2,
			type=PrimaryKeyType.CLUSTERED
			)
	private UUID id;
	private String name;
	@PrimaryKeyColumn (
			name="storeName",
			ordinal=1,
			type=PrimaryKeyType.PARTITIONED
			)
	private String storeName;
	private Double price;
	@PrimaryKeyColumn (
			name="category",
			ordinal=0,
			type=PrimaryKeyType.CLUSTERED
			)
	private ItemType category;
	private String picture;
	
	public ItemDTO() {
		
	}
	
	public ItemDTO(Item item) {
		this.id = item.getId();
		this.name = item.getName();
		this.storeName = item.getStoreName();
		this.price = item.getPrice();
		this.category = item.getCategory();
		this.picture = item.getPicture();
	}
	
	public Item getItem() {
		Item i = new Item();
		i.setId(this.id);
		i.setName(this.name);
		i.setStoreName(this.storeName);
		i.setPrice(this.price);
		i.setCategory(this.category);
		i.setPicture(this.picture);
		return i;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((storeName == null) ? 0 : storeName.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (storeName == null) {
			if (other.storeName != null)
				return false;
		} else if (!storeName.equals(other.storeName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemDTO [id=" + id + ", name=" + name + ", storeName=" + storeName + ", price=" + price + ", category="
				+ category + ", picture=" + picture + "]";
	}
	

}
