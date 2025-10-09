package com.bootcamp.product_service.infrastructure.repository;

import com.bootcamp.product_service.application.port.out.CuentaPlazoFijoRepositoryPort;
import com.bootcamp.product_service.domain.aggregate.accounts.CuentaPlazoFijo;
import com.bootcamp.product_service.infrastructure.repository.mongo.CuentaPlazoFijoMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CuentaPlazoFijoRepositoryAdapter implements CuentaPlazoFijoRepositoryPort {
    private final CuentaPlazoFijoMongoRepository repository;
    public CuentaPlazoFijoRepositoryAdapter(CuentaPlazoFijoMongoRepository repository) {
        this.repository = repository;
    }
    @Override
    public Mono<CuentaPlazoFijo> save(CuentaPlazoFijo cuentaPlazoFijo) {
        return null;
    }

    @Override
    public Mono<CuentaPlazoFijo> findById(String id) {
        return null;
    }

    @Override
    public Flux<CuentaPlazoFijo> findAll() {
        return null;
    }

    @Override
    public Mono<Boolean> delete(CuentaPlazoFijo customer) {
        return null;
    }
}
