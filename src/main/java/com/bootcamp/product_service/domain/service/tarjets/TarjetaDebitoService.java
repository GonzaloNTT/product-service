package com.bootcamp.product_service.domain.service.tarjets;

import com.bootcamp.product_service.application.mapper.command.TarjetaDebitoCommand;
import com.bootcamp.product_service.domain.aggregate.accounts.CuentaAhorro;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TarjetaDebitoService {
    Mono<CuentaAhorro> create(@Valid TarjetaDebitoCommand command);
    Mono<CuentaAhorro> findById(String id);
    Flux<CuentaAhorro> findAll();
    Mono<Boolean> delete(String id);
}
