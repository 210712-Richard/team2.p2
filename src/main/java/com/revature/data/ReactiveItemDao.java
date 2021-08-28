package com.revature.data;

import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.revature.dto.ItemDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveItemDao extends ReactiveCassandraRepository<ItemDTO, String>{
	Flux<ItemDTO> findByStorename(String storeName);
	Mono<ItemDTO> findByStorenameAndUuid(String storeName, UUID uuid);
	
	
	//take the whole query and filter what you want
	@AllowFiltering
	Mono<ItemDTO> findByUuid(UUID id);

	
}
