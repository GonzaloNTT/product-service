package com.bootcamp.product_service.infrastructure.entrypoints;

import com.bootcamp.product_service.domain.service.wallet.YankiService;
import com.bootcamp.product_service.server.CuentaApi;
import com.bootcamp.product_service.server.YankiApi;
import com.bootcamp.product_service.server.models.YankiClienteRequest;
import com.bootcamp.product_service.server.models.YankiNoClienteRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class YankiController implements YankiApi {
    private final YankiService service;
    public YankiController(YankiService service) {
        this.service = service;
    }

    @Override
    public Mono<ResponseEntity<Void>> yankiClienteClienteIdGet(String clienteId, final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }

    @Override
    public  Mono<ResponseEntity<Void>> yankiClientePost(Mono<YankiClienteRequest> yankiClienteRequest,
                                                        final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(yankiClienteRequest).then(Mono.empty());

    }

    @Override
    public  Mono<ResponseEntity<Void>> yankiIdDelete(String id,  final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }

    @Override
    public  Mono<ResponseEntity<Void>> yankiNoClienteNoClienteIdGet(String noClienteId,
                                                                    final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }
    
    @Override
    public  Mono<ResponseEntity<Void>> yankiNoClientePost(Mono<YankiNoClienteRequest> yankiNoClienteRequest,
                                                          final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(yankiNoClienteRequest).then(Mono.empty());

    }
}
