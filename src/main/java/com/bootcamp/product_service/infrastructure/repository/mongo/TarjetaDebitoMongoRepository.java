package com.bootcamp.product_service.infrastructure.repository.mongo;

import com.bootcamp.product_service.domain.aggregate.Tarjets.TarjetaDebito;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TarjetaDebitoMongoRepository extends ReactiveMongoRepository<TarjetaDebito, String> {
}
