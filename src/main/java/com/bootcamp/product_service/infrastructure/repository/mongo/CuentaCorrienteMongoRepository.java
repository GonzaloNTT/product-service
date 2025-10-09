package com.bootcamp.product_service.infrastructure.repository.mongo;

import com.bootcamp.product_service.domain.aggregate.accounts.CuentaCorriente;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CuentaCorrienteMongoRepository extends ReactiveMongoRepository<CuentaCorriente, String> {
}
