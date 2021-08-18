package com.revature.data;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.ItemType;
import com.revature.dto.ItemDTO;
import com.revature.dto.UserDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveItemDao extends ReactiveCassandraRepository<ItemDTO, String>{
	Mono<ItemDTO> findByCategoryAndStoreNameAndId(String username);
	Flux<ItemDTO> findByCategory(ItemType itemType);

}
