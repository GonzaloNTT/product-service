package com.bootcamp.customer_service.application.command;

import com.bootcamp.customer_service.application.dto.CustomerRequest;
import com.bootcamp.customer_service.application.dto.CustomerResponse;
import reactor.core.publisher.Mono;

public interface CustomerCommandService {

    Mono<CustomerResponse> createCustomer(CustomerRequest request);

    Mono<CustomerResponse> updateCustomer(String id, CustomerRequest request);

    Mono<Void> deleteCustomer(String id);

}