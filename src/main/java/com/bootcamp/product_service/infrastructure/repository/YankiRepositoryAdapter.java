package com.bootcamp.product_service.infrastructure.repository;

import com.bootcamp.product_service.application.port.out.YankiRepositoryPort;
import com.bootcamp.product_service.domain.aggregate.wallet.Yanki;
import com.bootcamp.product_service.infrastructure.repository.mongo.YankiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class YankiRepositoryAdapter implements YankiRepositoryPort {
    private final YankiRepository repository;
    public YankiRepositoryAdapter(YankiRepository repository) {
        this.repository = repository;
    }
    @Override
    public Mono<Yanki> save(Yanki yanki) {
        return null;
    }

    @Override
    public Mono<Yanki> findById(String id) {
        return null;
    }

    @Override
    public Flux<Yanki> findAll() {
        return null;
    }

    @Override
    public Mono<Boolean> delete(Yanki customer) {
        return null;
    }
}
