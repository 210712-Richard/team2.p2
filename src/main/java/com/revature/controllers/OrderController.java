package com.revature.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

import com.revature.beans.Order;
import com.revature.services.OrderService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;

	//As a customer, I can place order

	//As a customer, I can get the list of order.
	@GetMapping("{username}")
	public ResponseEntity<Flux<Order>> getOrder(@PathVariable("username") String username, WebSession session) {
		String loggedUser = (String) session.getAttribute("loggedUser");
		if(loggedUser == null) {
			return ResponseEntity.status(401).build();
		}
		if(!loggedUser.equals(username)) {
			return ResponseEntity.status(403).build();
		}
	
		return ResponseEntity.ok(orderService.listOrders(username));
	}
	
	
	//As a customer, I can cancel order
	@PostMapping("{username}/garbage")
	public ResponseEntity<Mono<Void>> deleteOrder(@PathVariable("username") String username, @RequestBody Order order, WebSession session) {
		String loggedUser = (String) session.getAttribute("loggedUser");
		if(loggedUser == null) {
			return ResponseEntity.status(401).build();
		}
		if(!loggedUser.equals(username)) {
			return ResponseEntity.status(403).build();
		}
		
		UUID orderId = order.getUuid();
		
		return ResponseEntity.ok(orderService.deleteOrder(username, orderId));
	}
	
	@PostMapping("{username}/new")
	public ResponseEntity<Mono<Order>> createOrder(@PathVariable("username") String username, WebSession session){
		String loggedUser = (String) session.getAttribute("loggedUser");
		if(loggedUser == null) {
			return ResponseEntity.status(401).build();
		}
		if(!loggedUser.equals(username)) {
			return ResponseEntity.status(403).build();
		}
		
		return ResponseEntity.ok(orderService.createOrder(username));
		
	}
	
	

}
