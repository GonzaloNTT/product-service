package com.bootcamp.product_service.application.service.wallet;

import com.bootcamp.product_service.application.mapper.command.MonederoBootCoinCommand;
import com.bootcamp.product_service.application.port.out.MonederoBootCoinRepositoryPort;
import com.bootcamp.product_service.domain.aggregate.accounts.CuentaAhorro;
import com.bootcamp.product_service.domain.service.wallet.MonederoBootCoinService;
import com.bootcamp.product_service.infrastructure.cache.ProductReactiveCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@Slf4j
public class MonederBootCoinServiceImpl implements MonederoBootCoinService {
    private final MonederoBootCoinRepositoryPort repository;
    private final ProductReactiveCache cache;

    public MonederBootCoinServiceImpl(MonederoBootCoinRepositoryPort repository, ProductReactiveCache cache) {
        this.repository = repository;
        this.cache = cache;
    }

    @Override
    public Mono<CuentaAhorro> create(MonederoBootCoinCommand command) {
        return null;
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
    public Mono<Boolean> delete(String id) {
        return null;
    }
}
