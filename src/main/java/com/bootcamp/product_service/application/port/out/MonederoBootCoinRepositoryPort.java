package com.bootcamp.product_service.application.port.out;


import com.bootcamp.product_service.domain.aggregate.wallet.MonederoBootCoin;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MonederoBootCoinRepositoryPort {
    Mono<MonederoBootCoin> save(MonederoBootCoin monederoBootCoin);

    Mono<MonederoBootCoin> findById(String id);

    Flux<MonederoBootCoin> findAll();

    Mono<Boolean> delete(MonederoBootCoin customer);
}
