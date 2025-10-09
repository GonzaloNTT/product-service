package com.bootcamp.product_service.infrastructure.repository;

import com.bootcamp.product_service.application.port.out.CuentaCorrienteRepositoryPort;
import com.bootcamp.product_service.domain.aggregate.accounts.CuentaCorriente;
import com.bootcamp.product_service.infrastructure.repository.mongo.CuentaCorrienteMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CuentaCorrienteRepositoryAdapter implements CuentaCorrienteRepositoryPort {
    private final CuentaCorrienteMongoRepository repository;

    public CuentaCorrienteRepositoryAdapter(CuentaCorrienteMongoRepository repository) {
        this.repository = repository;
    }
    @Override
    public Mono<CuentaCorriente> save(CuentaCorriente cuentaCorriente) {
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
    public Mono<Boolean> delete(CuentaCorriente customer) {
        return null;
    }
}
