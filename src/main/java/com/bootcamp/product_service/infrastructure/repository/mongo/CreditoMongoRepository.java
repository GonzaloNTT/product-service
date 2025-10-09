package com.bootcamp.product_service.infrastructure.repository.mongo;

import com.bootcamp.product_service.domain.aggregate.credit.Credito;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CreditoMongoRepository extends ReactiveMongoRepository<Credito, String> {
    Flux<Credito> findByClienteIdAndTipo(String clienteId, String tipoCredito);
}
