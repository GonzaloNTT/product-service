package com.bootcamp.product_service.application.port.out;


import com.bootcamp.product_service.domain.aggregate.credit.Credito;
import com.bootcamp.product_service.domain.enums.TipoCredito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditoRepositoryPort {
    Mono<Credito> save(Credito credito);

    Mono<Credito> findById(String id);

    Flux<Credito> findAll();

    Mono<Boolean> delete(Credito customer);

    Flux<Credito> findByClienteIdAndTipoCredito(String clienteId, TipoCredito tipoCredito);
}
