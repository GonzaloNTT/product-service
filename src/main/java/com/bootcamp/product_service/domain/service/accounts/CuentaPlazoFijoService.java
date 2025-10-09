package com.bootcamp.product_service.domain.service.accounts;

import com.bootcamp.product_service.application.mapper.command.CuentaPlazoFijoCommand;
import com.bootcamp.product_service.domain.aggregate.accounts.CuentaAhorro;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CuentaPlazoFijoService {
    Mono<CuentaAhorro> create(@Valid CuentaPlazoFijoCommand command);
    Mono<CuentaAhorro> findById(String id);
    Flux<CuentaAhorro> findAll();
    Mono<Boolean> delete(String id);
}
