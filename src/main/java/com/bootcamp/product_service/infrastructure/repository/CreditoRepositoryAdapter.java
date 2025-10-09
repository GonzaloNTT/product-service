package com.bootcamp.product_service.infrastructure.repository;

import com.bootcamp.product_service.application.port.out.CreditoRepositoryPort;
import com.bootcamp.product_service.domain.aggregate.credit.Credito;
import com.bootcamp.product_service.domain.enums.TipoCredito;
import com.bootcamp.product_service.infrastructure.repository.mongo.CreditoMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CreditoRepositoryAdapter implements CreditoRepositoryPort {
    private final CreditoMongoRepository repository;
    public CreditoRepositoryAdapter(CreditoMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Credito> save(Credito credito) {
        return this.repository.save(credito);
    }

    @Override
    public Mono<Credito> findById(String id) {
        return this.repository.findById(id);
    }

    @Override
    public Flux<Credito> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Mono<Boolean> delete(Credito customer) {
        return this.repository.deleteById(customer.getId()).hasElement();
    }

    @Override
    public Flux<Credito> findByClienteIdAndTipoCredito(String clienteId, TipoCredito tipoCredito) {
        return repository.findByClienteIdAndTipo(clienteId, tipoCredito.name());
    }
}
