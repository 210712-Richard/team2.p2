package com.revature.data;

import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.revature.dto.ItemDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveItemDao extends ReactiveCassandraRepository<ItemDTO, String>{
	Flux<ItemDTO> findByStorename(String storeName);
	Mono<ItemDTO> findByStorenameAndUuid(String storeName, UUID uuid);
	
	Flux<ItemDTO> findByPrice(Double price);
	//take the whole query and filter what you want
	@AllowFiltering
	@Query("SELECT * from item where uuid = ?0")
	Mono<ItemDTO> findByUuid(UUID id);

	
}
