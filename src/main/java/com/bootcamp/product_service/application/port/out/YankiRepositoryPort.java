package com.bootcamp.product_service.application.port.out;


import com.bootcamp.product_service.domain.aggregate.wallet.Yanki;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface YankiRepositoryPort {
    Mono<Yanki> save(Yanki yanki);

    Mono<Yanki> findById(String id);

    Flux<Yanki> findAll();

    Mono<Boolean> delete(Yanki customer);
}
