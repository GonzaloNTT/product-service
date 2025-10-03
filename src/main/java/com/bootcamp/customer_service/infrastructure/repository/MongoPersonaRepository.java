package com.bootcamp.customer_service.infrastructure.repository;

import com.bootcamp.customer_service.domain.aggregate.Customer;
import com.bootcamp.customer_service.domain.enums.DocumentType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface MongoPersonaRepository extends ReactiveMongoRepository<Customer, String> {

    Mono<Boolean> existsByDocumentTypeAndDocumentNumber(DocumentType documentType, String documentNumber);
}
