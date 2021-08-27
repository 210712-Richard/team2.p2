package com.revature.data;

import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Item;
import com.revature.beans.User;
import com.revature.dto.UserDTO;

import reactor.core.publisher.Mono;

@Repository
public interface ReactiveUserDao extends ReactiveCassandraRepository<UserDTO, String>{
	Mono<UserDTO> findByUsername(String username);
	
}
