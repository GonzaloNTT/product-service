package com.bootcamp.customer_service.application.service;

import com.bootcamp.customer_service.application.dto.CustomerRequest;
import com.bootcamp.customer_service.domain.aggregate.LegalCustomer;
import com.bootcamp.customer_service.domain.aggregate.NaturalCustomer;
import com.bootcamp.customer_service.domain.valueobject.CustomerId;
import com.bootcamp.customer_service.infrastructure.mapper.LegalCustomerCommand;
import com.bootcamp.customer_service.infrastructure.mapper.NaturalCustomerCommand;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerApplicationService {
    Mono<CustomerId> registerNaturalCustomer(@Valid CustomerRequest naturalCustomerCommand);
    Mono<CustomerId> registerLegalCustomer(@Valid CustomerRequest legalCustomerCommand);

    Mono<NaturalCustomer> getNaturalCustomerById(String id);
    Mono<LegalCustomer> getLegalCustomerById(String id);

    Flux<NaturalCustomer> getAllNaturalCustomers();
    Flux<LegalCustomer> getAllLegalCustomers();

    Mono<NaturalCustomer> updateNaturalCustomer(String id, NaturalCustomerCommand naturalCustomerCommand);
    Mono<LegalCustomer> updateLegalCustomer(String id, LegalCustomerCommand legalCustomerCommand);

    Mono<Boolean> deleteNaturalCustomer(String id);
    Mono<Boolean> deleteLegalCustomer(String id);
}