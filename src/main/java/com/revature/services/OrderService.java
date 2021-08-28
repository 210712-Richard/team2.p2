package com.revature.services;

import java.util.UUID;

import com.revature.beans.Order;
import com.revature.dto.OrderDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {
	Flux<Order> listOrders(String customer);
	Mono<Void> deleteOrder(String customer, UUID uuid);
	Mono<Order> createOrder(String customer);

}
