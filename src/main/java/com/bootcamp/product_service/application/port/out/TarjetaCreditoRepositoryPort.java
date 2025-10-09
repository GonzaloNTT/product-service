package com.bootcamp.product_service.application.port.out;


import com.bootcamp.product_service.domain.aggregate.Tarjets.TarjetaCredito;
import com.bootcamp.product_service.domain.enums.TipoTarjeta;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TarjetaCreditoRepositoryPort {
    Mono<TarjetaCredito> save(TarjetaCredito tarjetaCredito);

    Mono<TarjetaCredito> findById(String id);

    Flux<TarjetaCredito> findAll();

    Mono<Boolean> delete(TarjetaCredito customer);

    Flux<TarjetaCredito> findByClienteIdAndTipo(String s, TipoTarjeta tipoTarjeta);
}
