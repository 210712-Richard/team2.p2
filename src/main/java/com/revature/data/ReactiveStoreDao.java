package com.revature.data;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.revature.dto.StoreDTO;

import reactor.core.publisher.Mono;

@Repository
public interface ReactiveStoreDao extends ReactiveCassandraRepository<StoreDTO, String>{
	Mono<StoreDTO> findByName(String name);
	
	@Query("update store set inventory = ?0 where username = ?1")
	Mono<StoreDTO> updateInventory(List<UUID> list, String name);

}
