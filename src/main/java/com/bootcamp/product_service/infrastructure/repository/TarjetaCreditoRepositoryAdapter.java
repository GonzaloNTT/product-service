package com.bootcamp.product_service.infrastructure.repository;

import com.bootcamp.product_service.application.port.out.TarjetaCreditoRepositoryPort;
import com.bootcamp.product_service.domain.aggregate.Tarjets.TarjetaCredito;
import com.bootcamp.product_service.domain.enums.TipoTarjeta;
import com.bootcamp.product_service.infrastructure.repository.mongo.TarjetaCreditoMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class TarjetaCreditoRepositoryAdapter implements TarjetaCreditoRepositoryPort {
    private final TarjetaCreditoMongoRepository repository;

    public TarjetaCreditoRepositoryAdapter(TarjetaCreditoMongoRepository repository) {
        this.repository = repository;
    }
    @Override
    public Mono<TarjetaCredito> save(TarjetaCredito tarjetaCredito) {
        return this.repository.save(tarjetaCredito);
    }

    @Override
    public Mono<TarjetaCredito> findById(String id) {
        return this.repository.findById(id);
    }

    @Override
    public Flux<TarjetaCredito> findAll() {
        return null;
    }

    @Override
    public Mono<Boolean> delete(TarjetaCredito customer) {
        return null;
    }

    @Override
    public Flux<TarjetaCredito> findByClienteIdAndTipo(String s, TipoTarjeta tipoTarjeta) {
        return this.repository.findByClienteIdAndTipo(s, tipoTarjeta.name());
    }
}
