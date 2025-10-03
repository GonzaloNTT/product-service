package com.bootcamp.customer_service.infrastructure.entrypoints.rest;

import com.bootcamp.customer_service.application.dto.CustomerRequest;
import com.bootcamp.customer_service.application.service.CustomerApplicationService;
import com.bootcamp.customer_service.domain.aggregate.LegalCustomer;
import com.bootcamp.customer_service.domain.valueobject.CustomerId;
import com.bootcamp.customer_service.infrastructure.mapper.CustomerMapper;
import com.bootcamp.customer_service.infrastructure.mapper.LegalCustomerCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/customers/legal")
@Slf4j
@Validated
public class LegalCustomerController {

    private final CustomerApplicationService customerApplicationService;
    private final CustomerMapper mapper;

    LegalCustomerController(CustomerApplicationService customerApplicationService, CustomerMapper mapper) {
        this.customerApplicationService = customerApplicationService;
        this.mapper = mapper;
    }


    @PostMapping
    public Mono<ResponseEntity<CustomerId>> create(@Valid @RequestBody CustomerRequest request) {
        log.debug("create Request={}", request);
        LegalCustomerCommand cmd = mapper.toLegalCustomerCommand(request);
        return customerApplicationService.registerLegalCustomer(request)
                .map(response -> ResponseEntity
                        .created(URI.create("/api/v1/customers/"))
                        .body(response))
                .doOnError(ex -> log.error("Error creating customer", ex));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<LegalCustomer>> getById(@PathVariable("id") String id) {
        log.debug("getById Request={}", id);
        return customerApplicationService.getLegalCustomerById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .doOnError(ex -> log.error("Error fetching customer by id {}", id, ex));
    }

    @GetMapping
    public Flux<LegalCustomer> getAll() {
        log.debug("getAll Request");
        return customerApplicationService.getAllLegalCustomers()                   // Flux<CustomerResponse>
                .doOnError(ex -> log.error("Error listing customers", ex));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<LegalCustomer>> update(@PathVariable("id") String id,
                                                        @Valid @RequestBody CustomerRequest request) {
        log.debug("update Request={}{}", id, request);
        LegalCustomerCommand cmd = mapper.toLegalCustomerCommand(request);
        return customerApplicationService.updateLegalCustomer(id, cmd)           // Mono<CustomerResponse>
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .doOnError(ex -> log.error("Error updating customer id {}", id, ex));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Boolean>> delete(@PathVariable("id") String id) {
        log.debug("delete Request={}", id);
        return customerApplicationService.deleteLegalCustomer(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
