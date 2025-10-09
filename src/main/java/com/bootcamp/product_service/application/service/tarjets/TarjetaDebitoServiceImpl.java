package com.bootcamp.product_service.application.service.tarjets;

import com.bootcamp.product_service.application.mapper.command.TarjetaDebitoCommand;
import com.bootcamp.product_service.application.port.out.TarjetaDebitoRepositoryPort;
import com.bootcamp.product_service.domain.aggregate.accounts.CuentaAhorro;
import com.bootcamp.product_service.domain.service.tarjets.TarjetaDebitoService;
import com.bootcamp.product_service.infrastructure.cache.ProductReactiveCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@Slf4j
public class TarjetaDebitoServiceImpl implements TarjetaDebitoService {
    private final TarjetaDebitoRepositoryPort repository;
    private final ProductReactiveCache cache;

    public TarjetaDebitoServiceImpl(TarjetaDebitoRepositoryPort repository, ProductReactiveCache cache) {
        this.repository = repository;
        this.cache = cache;
    }
    @Override
    public Mono<CuentaAhorro> create(TarjetaDebitoCommand command) {
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
