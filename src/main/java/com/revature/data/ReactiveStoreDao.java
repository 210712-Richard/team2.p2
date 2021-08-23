package com.revature.data;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.revature.dto.StoreDTO;

import reactor.core.publisher.Mono;

@Repository
public interface ReactiveStoreDao extends ReactiveCassandraRepository<StoreDTO, String>{
	Mono<StoreDTO> findByName(String name);

}
