package com.bootcamp.customer_service.infrastructure.entrypoints.rest;

import com.bootcamp.customer_service.application.dto.CustomerRequest;
import com.bootcamp.customer_service.application.service.CustomerApplicationService;
import com.bootcamp.customer_service.domain.aggregate.NaturalCustomer;
import com.bootcamp.customer_service.domain.valueobject.CustomerId;
import com.bootcamp.customer_service.infrastructure.mapper.CustomerMapper;
import com.bootcamp.customer_service.infrastructure.mapper.NaturalCustomerCommand;
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
@RequestMapping("/api/v1/customers/natural")
@RequiredArgsConstructor
@Slf4j
@Validated
public class NaturalCustomerController {

    private final CustomerApplicationService customerApplicationService;
    private final CustomerMapper mapper;

    @PostMapping
    public Mono<ResponseEntity<CustomerId>> create(@Valid @RequestBody CustomerRequest request) {
        log.debug("create Request={}", request);
        NaturalCustomerCommand cmd = mapper.toNaturalCustomerCommand(request);
        return customerApplicationService.registerNaturalCustomer(request)
                .map(response -> ResponseEntity
                        .created(URI.create("/api/v1/customers/"))
                        .body(response))
                .doOnError(ex -> log.error("Error creating customer", ex));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<NaturalCustomer>> getById(@PathVariable("id") String id) {
        log.debug("getById Request={}", id);
        return customerApplicationService.getNaturalCustomerById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .doOnError(ex -> log.error("Error fetching customer by id {}", id, ex));
    }

    @GetMapping
    public Flux<NaturalCustomer> getAll() {
        log.debug("getAll Request");
        return customerApplicationService.getAllNaturalCustomers()                   // Flux<CustomerResponse>
                .doOnError(ex -> log.error("Error listing customers", ex));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<NaturalCustomer>> update(@PathVariable("id") String id,
                                                        @Valid @RequestBody CustomerRequest request) {
        log.debug("update Request={}{}", id, request);
        NaturalCustomerCommand cmd = mapper.toNaturalCustomerCommand(request);
        return customerApplicationService.updateNaturalCustomer(id, cmd)           // Mono<CustomerResponse>
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .doOnError(ex -> log.error("Error updating customer id {}", id, ex));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Boolean>> delete(@PathVariable("id") String id) {
        log.debug("delete Request={}", id);
        return customerApplicationService.deleteNaturalCustomer(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
