package com.revature.data;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.revature.dto.StoreDTO;

@Repository
public interface ReactiveStoreDao extends ReactiveCassandraRepository<StoreDTO, String>{

}
