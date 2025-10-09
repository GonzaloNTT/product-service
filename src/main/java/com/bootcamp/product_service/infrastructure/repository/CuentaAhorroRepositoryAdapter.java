package com.bootcamp.product_service.infrastructure.repository;

import com.bootcamp.product_service.application.port.out.CuentaAhorroRepositoryPort;
import com.bootcamp.product_service.domain.aggregate.accounts.CuentaAhorro;
import com.bootcamp.product_service.domain.enums.TipoCuentaAhorro;
import com.bootcamp.product_service.infrastructure.repository.mongo.CuentaAhorroMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CuentaAhorroRepositoryAdapter implements CuentaAhorroRepositoryPort {
    private final CuentaAhorroMongoRepository repository;
    public CuentaAhorroRepositoryAdapter(CuentaAhorroMongoRepository repository) {
        this.repository = repository;
    }
    @Override
    public Mono<CuentaAhorro> save(CuentaAhorro cuentaAhorro) {
        return this.repository.save(cuentaAhorro);
    }

    @Override
    public Mono<CuentaAhorro> findById(String id) {
        return null;
    }

    @Override
    public Flux<CuentaAhorro> findAll() {
        return null;
    }

    @Override
    public Mono<Boolean> delete(CuentaAhorro customer) {
        return null;
    }

    @Override
    public Flux<CuentaAhorro> findByClienteIdAndTipoCuentaAhorro(String id, TipoCuentaAhorro tipoCuentaAhorro) {
        return this.repository.findByClienteIdAndTipo(id, tipoCuentaAhorro);
    }
}
