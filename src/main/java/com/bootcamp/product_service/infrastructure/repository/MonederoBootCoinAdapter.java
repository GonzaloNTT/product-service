package com.bootcamp.product_service.infrastructure.repository;

import com.bootcamp.product_service.application.port.out.MonederoBootCoinRepositoryPort;
import com.bootcamp.product_service.domain.aggregate.wallet.MonederoBootCoin;
import com.bootcamp.product_service.infrastructure.repository.mongo.MonederoBootCoinMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class MonederoBootCoinAdapter implements MonederoBootCoinRepositoryPort {
    private final MonederoBootCoinMongoRepository repository;
    public MonederoBootCoinAdapter(MonederoBootCoinMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<MonederoBootCoin> save(MonederoBootCoin monederoBootCoin) {
        return null;
    }

    @Override
    public Mono<MonederoBootCoin> findById(String id) {
        return null;
    }

    @Override
    public Flux<MonederoBootCoin> findAll() {
        return null;
    }

    @Override
    public Mono<Boolean> delete(MonederoBootCoin customer) {
        return null;
    }
}
