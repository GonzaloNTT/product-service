package com.bootcamp.product_service.application.port.out;


import com.bootcamp.product_service.domain.aggregate.Tarjets.TarjetaDebito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TarjetaDebitoRepositoryPort {
    Mono<TarjetaDebito> save(TarjetaDebito tarjetaDebito);

    Mono<TarjetaDebito> findById(String id);

    Flux<TarjetaDebito> findAll();

    Mono<Boolean> delete(TarjetaDebito customer);
}
