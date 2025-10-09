package com.bootcamp.product_service.domain.service.wallet;

import com.bootcamp.product_service.application.mapper.command.MonederoBootCoinCommand;
import com.bootcamp.product_service.domain.aggregate.accounts.CuentaAhorro;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MonederoBootCoinService {
    Mono<CuentaAhorro> create(@Valid MonederoBootCoinCommand command);
    Mono<CuentaAhorro> findById(String id);
    Flux<CuentaAhorro> findAll();
    Mono<Boolean> delete(String id);
}
