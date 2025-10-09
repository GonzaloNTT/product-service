package com.bootcamp.product_service.application.port.out;


import com.bootcamp.product_service.domain.aggregate.accounts.CuentaAhorro;
import com.bootcamp.product_service.domain.enums.TipoCuentaAhorro;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CuentaAhorroRepositoryPort {
    Mono<CuentaAhorro> save(CuentaAhorro cuentaAhorro);

    Mono<CuentaAhorro> findById(String id);

    Flux<CuentaAhorro> findAll();

    Mono<Boolean> delete(CuentaAhorro customer);

    Flux<CuentaAhorro> findByClienteIdAndTipoCuentaAhorro(String s, TipoCuentaAhorro tipoCuentaAhorro);
}
