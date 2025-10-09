package com.bootcamp.product_service.application.service.accounts;

import com.bootcamp.product_service.application.mapper.command.CuentaCorrienteCommand;
import com.bootcamp.product_service.application.port.out.CuentaCorrienteRepositoryPort;
import com.bootcamp.product_service.domain.aggregate.accounts.CuentaCorriente;
import com.bootcamp.product_service.domain.service.accounts.CuentaCorrienteService;
import com.bootcamp.product_service.infrastructure.cache.ProductReactiveCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@Slf4j
public class CuentaCorrienteServiceImpl implements CuentaCorrienteService {
    private final CuentaCorrienteRepositoryPort repository;
    private final ProductReactiveCache cache;

    public CuentaCorrienteServiceImpl(CuentaCorrienteRepositoryPort repository, ProductReactiveCache cache) {
        this.repository = repository;
        this.cache = cache;
    }

    @Override
    public Mono<CuentaCorriente> create(CuentaCorrienteCommand command) {
        return null;
    }

    @Override
    public Mono<CuentaCorriente> findById(String id) {
        return null;
    }

    @Override
    public Flux<CuentaCorriente> findAll() {
        return null;
    }

    @Override
    public Mono<Boolean> delete(String id) {
        return null;
    }
}
