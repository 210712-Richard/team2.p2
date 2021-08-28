package com.revature.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Order;
import com.revature.beans.ShippingStatus;
import com.revature.data.ReactiveItemDao;
import com.revature.data.ReactiveOrderDao;
import com.revature.data.ReactiveUserDao;
import com.revature.dto.OrderDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderServiceImpl implements OrderService {
	private ReactiveUserDao userDao;
	private ReactiveOrderDao orderDao;
	private ReactiveItemDao itemDao;
	
	@Autowired
	public OrderServiceImpl(ReactiveUserDao userDao, ReactiveOrderDao orderDao, ReactiveItemDao itemDao) {
		super();
		this.userDao = userDao;
		this.orderDao = orderDao;
		this.itemDao = itemDao;
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
		
		// create base orderDTO
		Order createOrder = new Order();
		createOrder.setOrderDate(LocalDate.now());
		createOrder.setStatus(ShippingStatus.SENT);
		//UUID id = UUID.randomUUID();
		createOrder.setUuid(UUID.randomUUID());
		createOrder.setCustomer(customer);
		
		// can't get the items list set
		createOrder.setItems(new ArrayList<>());
		
		
		return orderDao.save(new OrderDTO(createOrder)).map(or -> or.getOrder());
		
		// 
		/*
		// get shopping cart and set as orderList
		Mono<List<Item>> orderList = Flux.from(userDao.findByUsername(customer))
				.map(userDto -> userDto.getShoppingCart())
				.flatMap(listUuids -> Flux.fromIterable(listUuids))
				.flatMap(uuid -> itemDao.findByUuid(uuid))
				.map(itemDto -> itemDto.getItem()).collectList();
		
		// set customer shopping cart to empty
		Mono<User> emptyUser =  userDao.findByUsername(customer).flatMap(user -> {
			List<UUID> cart = new ArrayList<>();
			user.setShoppingCart(cart);
			return userDao.save(user);
		}).map(user -> user.getUser());
		
		return orderDao.save(createDTO).map(or -> or.getOrder());

*/
		
		// return order and save it
//		return orderList.zipWith(orderDao.save(createDTO).map(ordr -> ordr.getOrder()))
//				.map(tup -> {
//					Order o = tup.getT2();
//					o.setItems(tup.getT1());
//					return orderDao.save(new OrderDTO(o));
//		});
		
		
	}
	

}
