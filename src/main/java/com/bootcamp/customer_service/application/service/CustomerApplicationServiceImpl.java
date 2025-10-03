package com.bootcamp.customer_service.application.service;

import com.bootcamp.customer_service.application.dto.CustomerRequest;
import com.bootcamp.customer_service.application.port.out.CustomerEventPublisherPort;
import com.bootcamp.customer_service.application.port.out.CustomerRepositoryPort;
import com.bootcamp.customer_service.domain.aggregate.*;
import com.bootcamp.customer_service.domain.enums.DocumentType;
import com.bootcamp.customer_service.domain.enums.LegalCustomerType;
import com.bootcamp.customer_service.domain.enums.NaturalCustomerType;
import com.bootcamp.customer_service.domain.valueobject.*;
import com.bootcamp.customer_service.events.CustomerCreatedEvent;
import com.bootcamp.customer_service.infrastructure.cache.CustomerReactiveCache;
import com.bootcamp.customer_service.infrastructure.mapper.LegalCustomerCommand;
import com.bootcamp.customer_service.infrastructure.mapper.NaturalCustomerCommand;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.*;

@Service
public class CustomerApplicationServiceImpl implements CustomerApplicationService {

    private final CustomerRepositoryPort customerRepository;
    private final CustomerReactiveCache cache;
    private final CustomerEventPublisherPort eventPublisher;

    public CustomerApplicationServiceImpl(CustomerRepositoryPort customerRepository, CustomerReactiveCache cache, CustomerEventPublisherPort eventPublisher) {
        this.customerRepository = customerRepository;
        this.cache = cache;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Mono<CustomerId> registerNaturalCustomer(CustomerRequest naturalCustomerCommand) {
        DocumentType docType = DocumentType.valueOf(naturalCustomerCommand.getDocumentType());
        CustomerId id = new CustomerId(UUID.randomUUID().toString());

        NaturalCustomer customer = new NaturalCustomer(
                id,
                new Document(docType, naturalCustomerCommand.getDocumentNumber()),
                new Email(naturalCustomerCommand.getEmail()),
                new Phone(naturalCustomerCommand.getPhone(), naturalCustomerCommand.getImei()),
                NaturalCustomerType.valueOf(naturalCustomerCommand.getPersonalType()),
                naturalCustomerCommand.getPassword()
        );

        return customerRepository.existsByDocument(docType, naturalCustomerCommand.getDocumentNumber())
                .filter(exists -> !exists)
                .switchIfEmpty(Mono.error(new IllegalArgumentException(
                        "Ya existe un cliente con documento " + docType + " " + naturalCustomerCommand.getDocumentNumber())))
                .flatMap(ignore -> customerRepository.save(customer))
                .map( saved -> {
                    CustomerCreatedEvent event = new CustomerCreatedEvent(
                            saved.getId().value(),
                            saved.getDocument().number(),
                            saved.getEmail().value()
                    );
                    eventPublisher.publishCustomerCreated(event);
                    return id;
                });
    }

    @Override
    public Mono<CustomerId> registerLegalCustomer(CustomerRequest legalCustomerCommand) {
        DocumentType docType = DocumentType.valueOf(legalCustomerCommand.getDocumentType());
        CustomerId id = new CustomerId(UUID.randomUUID().toString());

        LegalCustomer customer = new LegalCustomer(
                id,
                new Document(docType, legalCustomerCommand.getDocumentNumber()),
                new Email(legalCustomerCommand.getEmail()),
                new Phone(legalCustomerCommand.getPhone(), legalCustomerCommand.getImei()),
                legalCustomerCommand.getPassword(),
                LegalCustomerType.valueOf(legalCustomerCommand.getBusinessType()),
                legalCustomerCommand.getRepresentatives()
        );

        return customerRepository.existsByDocument(docType, legalCustomerCommand.getDocumentNumber())
                .flatMap(exists -> exists
                        ? Mono.error(new IllegalArgumentException("Ya existe un cliente con documento " +
                        docType + " " + legalCustomerCommand.getDocumentNumber()))
                        : customerRepository.save(customer).thenReturn(id)
                );
    }

    @Override
    public Mono<NaturalCustomer> getNaturalCustomerById(String id) {
        String cacheKey = "customer:natural:" + id;
        return cache.get(cacheKey)
                .cast(NaturalCustomer.class) // Si está en cache, casteo directo
                .switchIfEmpty( // Si no está en cache, voy a DB
                        customerRepository.findById(new CustomerId(id))
                                .flatMap(customer -> {
                                    if (customer instanceof NaturalCustomer nc) {
                                        // Guardar en cache con TTL (ej. 10 min)
                                        return cache.put(cacheKey, nc, Duration.ofMinutes(10))
                                                .thenReturn(nc);
                                    }
                                    return Mono.empty();
                                })
                );
    }

    @Override
    public Mono<LegalCustomer> getLegalCustomerById(String id) {
        String cacheKey = "customer:legal:" + id;
        return cache.get(cacheKey)
                .cast(LegalCustomer.class) // Si está en cache, casteo directo
                .switchIfEmpty( // Si no está en cache, voy a DB
                        customerRepository.findById(new CustomerId(id))
                                .flatMap(customer -> {
                                    if (customer instanceof LegalCustomer nc) {
                                        // Guardar en cache con TTL (ej. 10 min)
                                        return cache.put(cacheKey, nc, Duration.ofMinutes(10))
                                                .thenReturn(nc);
                                    }
                                    return Mono.empty();
                                })
                );
    }

    @Override
    public Flux<NaturalCustomer> getAllNaturalCustomers() {
        return customerRepository.findAll()
                .filter(c -> c instanceof NaturalCustomer)
                .cast(NaturalCustomer.class);
    }

    @Override
    public Flux<LegalCustomer> getAllLegalCustomers() {
        return customerRepository.findAll()
                .filter(c -> c instanceof LegalCustomer)
                .cast(LegalCustomer.class);
    }

    @Override
    public Mono<NaturalCustomer> updateNaturalCustomer(String id, NaturalCustomerCommand naturalCustomerCommand) {
        return customerRepository.findById(new CustomerId(id))
                .flatMap(existing -> {
                    if (existing instanceof NaturalCustomer) {
                        NaturalCustomer updated = new NaturalCustomer(
                                existing.getId(),
                                new Document(DocumentType.valueOf(naturalCustomerCommand.getDocumentType()), naturalCustomerCommand.getDocumentNumber()),
                                new Email(naturalCustomerCommand.getEmail()),
                                new Phone(naturalCustomerCommand.getPhone(), naturalCustomerCommand.getImei()),
                                naturalCustomerCommand.getType(),
                                naturalCustomerCommand.getPassword()
                        );
                        return customerRepository.save(updated).thenReturn(updated);
                    }
                    return Mono.error(new IllegalArgumentException("El cliente no es de tipo NATURAL"));
                });
    }

    @Override
    public Mono<LegalCustomer> updateLegalCustomer(String id, LegalCustomerCommand legalCustomerCommand) {
        return customerRepository.findById(new CustomerId(id))
                .flatMap(existing -> {
                    if (existing instanceof LegalCustomer) {
                        LegalCustomer updated = new LegalCustomer(
                                existing.getId(),
                                new Document(DocumentType.valueOf(legalCustomerCommand.getDocumentType()), legalCustomerCommand.getDocumentNumber()),
                                new Email(legalCustomerCommand.getEmail()),
                                new Phone(legalCustomerCommand.getPhone(), legalCustomerCommand.getImei()),
                                legalCustomerCommand.getPassword(),
                                legalCustomerCommand.getType(),
                                legalCustomerCommand.getRepresentatives()
                        );
                        return customerRepository.save(updated).thenReturn(updated);
                    }
                    return Mono.error(new IllegalArgumentException("El cliente no es de tipo LEGAL"));
                });
    }

    @Override
    public Mono<Boolean> deleteNaturalCustomer(String id) {
        String cacheKey = "customer:natural:" + id;

        return customerRepository.findById(new CustomerId(id))
                .flatMap(customer -> {
                    if (customer instanceof NaturalCustomer) {
                        return customerRepository.delete(customer)
                                .then(cache.evict(cacheKey)) // Evictar del cache
                                .thenReturn(true);
                    }
                    return Mono.just(false);
                });
    }

    @Override
    public Mono<Boolean> deleteLegalCustomer(String id) {
        String cacheKey = "customer:legal:" + id;

        return customerRepository.findById(new CustomerId(id))
                .flatMap(customer -> {
                    if (customer instanceof LegalCustomer) {
                        return customerRepository.delete(customer)
                                .then(cache.evict(cacheKey)) // Evictar del cache
                                .thenReturn(true);
                    }
                    return Mono.just(false);
                });
    }
}