package com.revature.data;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.revature.dto.ItemDTO;

@Repository
public interface ReactiveItemDao extends ReactiveCassandraRepository<ItemDTO, String>{

}
