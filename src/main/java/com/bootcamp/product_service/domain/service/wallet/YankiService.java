package com.bootcamp.product_service.domain.service.wallet;

import com.bootcamp.product_service.application.mapper.command.YankiCommand;
import com.bootcamp.product_service.domain.aggregate.accounts.CuentaAhorro;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface YankiService {
    Mono<CuentaAhorro> create(@Valid YankiCommand command);
    Mono<CuentaAhorro> findById(String id);
    Flux<CuentaAhorro> findAll();
    Mono<Boolean> delete(String id);
}
