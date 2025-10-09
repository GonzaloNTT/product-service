package com.bootcamp.product_service.infrastructure.entrypoints;

import com.bootcamp.product_service.domain.service.wallet.MonederoBootCoinService;
import com.bootcamp.product_service.server.CuentaApi;
import com.bootcamp.product_service.server.MonederoApi;
import com.bootcamp.product_service.server.models.MonederoBootCoinRequest;
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

public class MonederoController implements MonederoApi {
    private final MonederoBootCoinService service;

    public MonederoController(MonederoBootCoinService service) {
        this.service = service;
    }

    @Override
    public Mono<ResponseEntity<Void>> monederoClienteClienteIdGet(String clienteId, final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }

    @Override
    public  Mono<ResponseEntity<Void>> monederoIdDelete(String id,  final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }

    @Override
    public  Mono<ResponseEntity<Void>> monederoPost(Mono<MonederoBootCoinRequest> monederoBootCoinRequest,
                                                    final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(monederoBootCoinRequest).then(Mono.empty());

    }
}
