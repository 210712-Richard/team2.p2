package com.revature.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Order;
import com.revature.data.ReactiveOrderDao;
import com.revature.data.ReactiveUserDao;
import com.revature.dto.OrderDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderServiceImpl implements OrderService {
	private ReactiveUserDao userDao;
	private ReactiveOrderDao orderDao;
	
	@Autowired
	public OrderServiceImpl(ReactiveUserDao userDao, ReactiveOrderDao orderDao) {
		super();
		this.userDao = userDao;
		this.orderDao = orderDao;
	}
	
	// get all from a customer
	@Override
	public Flux<Order> listOrders(String customer){
		return orderDao.findByCustomer(customer).map(dto -> dto.getOrder());
	}
	
	// delete a order
	@Override
	public Mono<Void> deleteOrder(String customer, UUID uuid) {
		Order order = new Order();
		order.setCustomer(customer);
		order.setUuid(uuid);
		return orderDao.delete(new OrderDTO(order));
	}
	
	// create an order
	@Override
	public Mono<Order> createOrder(String customer){
		// alby is doing this right now
		// change for git to happen
		return null;
	}

}
