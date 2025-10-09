package com.bootcamp.product_service.domain.service.accounts;

import com.bootcamp.product_service.application.mapper.command.CuentaCorrienteCommand;
import com.bootcamp.product_service.domain.aggregate.accounts.CuentaCorriente;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CuentaCorrienteService {
    Mono<CuentaCorriente> create(@Valid CuentaCorrienteCommand command);
    Mono<CuentaCorriente> findById(String id);
    Flux<CuentaCorriente> findAll();
    Mono<Boolean> delete(String id);
}
