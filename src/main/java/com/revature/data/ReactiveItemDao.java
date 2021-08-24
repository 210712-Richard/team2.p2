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
	Flux<ItemDTO> findByStoreName(String storeName);
	Mono<ItemDTO> findByStoreNameAndUuid(String storeName, UUID uuid);
	@AllowFiltering
	@Query("SELECT * from item where uuid = ?0")
	Mono<ItemDTO> findByUuid(UUID id);

	
}
