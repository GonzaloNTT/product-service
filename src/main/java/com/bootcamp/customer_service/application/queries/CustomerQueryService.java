package com.bootcamp.customer_service.application.queries;

import com.bootcamp.customer_service.application.dto.CustomerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerQueryService {

    Flux<CustomerResponse> getAllCustomers(String type);

    Mono<CustomerResponse> getCustomerById(String id);

}
