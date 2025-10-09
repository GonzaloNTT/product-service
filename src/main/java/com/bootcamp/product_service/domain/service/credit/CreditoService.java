package com.bootcamp.product_service.domain.service.credit;

import com.bootcamp.product_service.application.mapper.command.CreditoCommand;
import com.bootcamp.product_service.domain.aggregate.accounts.CuentaAhorro;
import com.bootcamp.product_service.domain.aggregate.credit.Credito;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditoService {
    Mono<Credito> create(@Valid CreditoCommand command);
    Mono<Credito> findById(String id);
    Flux<Credito> findAll();
    Mono<Boolean> delete(String id);
}
