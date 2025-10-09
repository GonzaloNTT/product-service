package com.bootcamp.product_service.infrastructure.repository.mongo;

import com.bootcamp.product_service.domain.aggregate.accounts.CuentaPlazoFijo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CuentaPlazoFijoMongoRepository extends ReactiveMongoRepository<CuentaPlazoFijo, String> {
}
