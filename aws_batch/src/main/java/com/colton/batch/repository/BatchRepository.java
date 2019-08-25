package com.colton.batch.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository<I,K>{
    K save(K o);
}
