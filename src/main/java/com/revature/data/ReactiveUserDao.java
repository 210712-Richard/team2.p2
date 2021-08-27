package com.revature.data;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.User;
import com.revature.dto.UserDTO;

import reactor.core.publisher.Mono;

@Repository
public interface ReactiveUserDao extends ReactiveCassandraRepository<UserDTO, String>{
	Mono<UserDTO> findByUsername(String username);
	
	@Query("update user set wishlist = ?0 where username = ?1")
	Mono<UserDTO> updateWishlist(List<UUID> list, String username);
}
