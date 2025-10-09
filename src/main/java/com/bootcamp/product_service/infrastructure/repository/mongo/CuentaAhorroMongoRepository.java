package com.bootcamp.product_service.infrastructure.repository.mongo;

import com.bootcamp.product_service.domain.aggregate.accounts.CuentaAhorro;

import com.bootcamp.product_service.domain.enums.TipoCuentaAhorro;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CuentaAhorroMongoRepository extends ReactiveMongoRepository<CuentaAhorro, String> {
    Flux<CuentaAhorro> findByClienteIdAndTipo(String id, TipoCuentaAhorro tipoCuentaAhorro);
}
