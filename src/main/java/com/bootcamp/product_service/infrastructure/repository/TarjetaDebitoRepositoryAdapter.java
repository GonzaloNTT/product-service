package com.bootcamp.product_service.infrastructure.repository;

import com.bootcamp.product_service.application.port.out.TarjetaDebitoRepositoryPort;
import com.bootcamp.product_service.domain.aggregate.Tarjets.TarjetaDebito;
import com.bootcamp.product_service.infrastructure.repository.mongo.TarjetaDebitoMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class TarjetaDebitoRepositoryAdapter implements TarjetaDebitoRepositoryPort {
    private final TarjetaDebitoMongoRepository repository;
    public TarjetaDebitoRepositoryAdapter(TarjetaDebitoMongoRepository repository) {
        this.repository = repository;
    }
    @Override
    public Mono<TarjetaDebito> save(TarjetaDebito tarjetaDebito) {
        return null;
    }

    @Override
    public Mono<TarjetaDebito> findById(String id) {
        return null;
    }

    @Override
    public Flux<TarjetaDebito> findAll() {
        return null;
    }

    @Override
    public Mono<Boolean> delete(TarjetaDebito customer) {
        return null;
    }
}
