package com.bootcamp.product_service.infrastructure.repository.mongo;

import com.bootcamp.product_service.domain.aggregate.Tarjets.TarjetaCredito;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TarjetaCreditoMongoRepository extends ReactiveMongoRepository<TarjetaCredito, String> {
    Flux<TarjetaCredito> findByClienteIdAndTipo(String clienteId, String tipo);
}
