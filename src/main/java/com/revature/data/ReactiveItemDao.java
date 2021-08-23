package com.revature.data;

import java.util.UUID;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.datastax.oss.driver.api.core.data.TupleValue;
import com.revature.beans.ItemType;
import com.revature.dto.ItemDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveItemDao extends ReactiveCassandraRepository<ItemDTO, String>{
	Flux<ItemDTO> findByStoreName(String storeName);
	Mono<ItemDTO> findByStoreNameAndUuid(String storeName, UUID uuid);
	
}
