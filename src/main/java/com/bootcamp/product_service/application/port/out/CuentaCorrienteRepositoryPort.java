package com.bootcamp.product_service.application.port.out;


import com.bootcamp.product_service.domain.aggregate.accounts.CuentaCorriente;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CuentaCorrienteRepositoryPort {
    Mono<CuentaCorriente> save(CuentaCorriente cuentaCorriente);

    Mono<CuentaCorriente> findById(String id);

    Flux<CuentaCorriente> findAll();

    Mono<Boolean> delete(CuentaCorriente customer);
}
