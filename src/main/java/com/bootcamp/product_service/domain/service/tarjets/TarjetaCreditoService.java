package com.bootcamp.product_service.domain.service.tarjets;

import com.bootcamp.operation.GastoEvent;
import com.bootcamp.product_service.application.mapper.command.TarjetaCreditoCommand;
import com.bootcamp.product_service.domain.aggregate.Tarjets.TarjetaCredito;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TarjetaCreditoService {
    Mono<TarjetaCredito> create(@Valid TarjetaCreditoCommand command);
    Mono<TarjetaCredito> findById(String id);
    Flux<TarjetaCredito> findAll();
    Mono<Boolean> delete(String id);

    Mono<Void> ejecutarGasto(GastoEvent message);
}
