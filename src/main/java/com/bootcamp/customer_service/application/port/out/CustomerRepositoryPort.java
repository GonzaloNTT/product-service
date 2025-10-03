package com.bootcamp.customer_service.application.port.out;

import com.bootcamp.customer_service.domain.aggregate.Customer;
import com.bootcamp.customer_service.domain.enums.DocumentType;
import com.bootcamp.customer_service.domain.valueobject.CustomerId;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.Locale;
import java.util.Optional;

public interface CustomerRepositoryPort {
    Mono<Customer> save(Customer customer);

    Mono<Boolean> existsByDocument(DocumentType documentType, String documentNumber);

    Mono<Customer> findById(CustomerId id);

    Flux<Customer> findAll();

    Mono<Boolean> delete(Customer customer);
}
