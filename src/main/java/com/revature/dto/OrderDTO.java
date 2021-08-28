package com.revature.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.revature.beans.Order;
import com.revature.beans.ShippingStatus;

@Table("order")
public class OrderDTO {
	@PrimaryKeyColumn(
			name="customer",
			ordinal=0,
			type=PrimaryKeyType.PARTITIONED
			)
	private String customer;
	@PrimaryKeyColumn(
			name="uuid",
			ordinal=0,
			type=PrimaryKeyType.CLUSTERED
			)
	private UUID uuid;
	@Column
	private List<UUID> items;
	@Column
	private Double cost;
	@Column
	private LocalDate orderDate;
	@Column
	private ShippingStatus status;
	
	public OrderDTO() {
		
	}
	
	public OrderDTO(Order order) {
		this.customer = order.getCustomer();
		this.uuid = order.getUuid();
		this.items = new ArrayList<>();
		order.getItems().stream().forEach(item -> this.items.add(item.getUuid()));
		this.cost = order.getCost();
		this.orderDate = order.getOrderDate();
		this.status = order.getStatus();
	}
	
	public Order getOrder() {
		return new Order(this.customer, this.uuid, this.cost, this.orderDate, this.status);
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public List<UUID> getItems() {
		if(items == null) {
			items = new ArrayList<>();
		}
		return items;
	}

	public void setItems(List<UUID> items) {
		this.items = items;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public ShippingStatus getStatus() {
		return status;
	}

	public void setStatus(ShippingStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		OrderDTO other = (OrderDTO) obj;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (status != other.status)
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
		return "OrderDTO [customer=" + customer + ", uuid=" + uuid + ", items=" + items + ", cost=" + cost
				+ ", orderDate=" + orderDate + ", status=" + status + "]";
	}
	

}
