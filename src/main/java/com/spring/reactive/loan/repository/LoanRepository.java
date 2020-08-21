package com.spring.reactive.loan.repository;

import com.spring.reactive.loan.entity.LoanEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface LoanRepository extends ReactiveMongoRepository<LoanEntity, String> {

    Flux<LoanEntity> findByUserId(String userId);
}
