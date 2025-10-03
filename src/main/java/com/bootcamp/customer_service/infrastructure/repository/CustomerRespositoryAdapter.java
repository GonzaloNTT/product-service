package com.bootcamp.customer_service.infrastructure.repository;

import com.bootcamp.customer_service.application.port.out.CustomerRepositoryPort;
import com.bootcamp.customer_service.domain.aggregate.Customer;
import com.bootcamp.customer_service.domain.enums.DocumentType;
import com.bootcamp.customer_service.domain.valueobject.CustomerId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerRespositoryAdapter implements CustomerRepositoryPort {
    private final MongoPersonaRepository repository;
    public CustomerRespositoryAdapter(MongoPersonaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Customer> save(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public Mono<Boolean> existsByDocument(DocumentType documentType, String documentNumber) {
        return repository.existsByDocumentTypeAndDocumentNumber(documentType, documentNumber);
    }

    @Override
    public Mono<Customer> findById(CustomerId id) {
        return repository.findById(id.value());
    }

    @Override
    public Flux<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Boolean> delete(Customer customer) {
        return repository.delete(customer).then(Mono.just(true));
    }
}
