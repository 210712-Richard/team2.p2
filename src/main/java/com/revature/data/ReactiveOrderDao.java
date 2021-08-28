package com.revature.data;

import java.util.UUID;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.revature.dto.OrderDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveOrderDao extends ReactiveCassandraRepository<OrderDTO, String>{
	Mono<OrderDTO> findByCustomerAndUuid(String customer, UUID uuid);
	Flux<OrderDTO> findByCustomer(String customer);

}
