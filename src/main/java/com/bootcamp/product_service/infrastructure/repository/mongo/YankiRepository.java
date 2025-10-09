package com.bootcamp.product_service.infrastructure.repository.mongo;

import com.bootcamp.product_service.domain.aggregate.wallet.Yanki;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface YankiRepository extends ReactiveMongoRepository<Yanki, String> {
}
