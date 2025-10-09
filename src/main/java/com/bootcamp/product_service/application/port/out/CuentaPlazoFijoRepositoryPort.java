package com.bootcamp.product_service.application.port.out;


import com.bootcamp.product_service.domain.aggregate.accounts.CuentaPlazoFijo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CuentaPlazoFijoRepositoryPort {
    Mono<CuentaPlazoFijo> save(CuentaPlazoFijo cuentaPlazoFijo);

    Mono<CuentaPlazoFijo> findById(String id);

    Flux<CuentaPlazoFijo> findAll();

    Mono<Boolean> delete(CuentaPlazoFijo customer);
}
