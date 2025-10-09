package com.bootcamp.product_service.domain.service.accounts;

import com.bootcamp.product_service.application.mapper.command.CuentaAhorroCommand;
import com.bootcamp.product_service.domain.aggregate.accounts.CuentaAhorro;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CuentaAhorroService {
    Mono<CuentaAhorro> create(@Valid CuentaAhorroCommand command);
    Mono<CuentaAhorro> findById(String id);
    Flux<CuentaAhorro> findAll();
    Mono<Boolean> delete(String id);
}
