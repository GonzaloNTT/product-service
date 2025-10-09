package com.bootcamp.product_service.infrastructure.entrypoints;

import com.bootcamp.product_service.domain.service.tarjets.TarjetaCreditoService;
import com.bootcamp.product_service.server.CuentaApi;
import com.bootcamp.product_service.server.TarjetaDebitoApi;
import com.bootcamp.product_service.server.models.TarjetaDebitoIdCuentaPrincipalPatchRequest;
import com.bootcamp.product_service.server.models.TarjetaDebitoRequest;
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

public class TarjetaDebitoController implements TarjetaDebitoApi {
    private final TarjetaCreditoService service;
    public TarjetaDebitoController(TarjetaCreditoService service) {
        this.service = service;
    }


    @Override
    public Mono<ResponseEntity<Void>> tarjetaDebitoIdCuentaPrincipalPatch(
            String id, Mono<TarjetaDebitoIdCuentaPrincipalPatchRequest> tarjetaDebitoIdCuentaPrincipalPatchRequest,
            final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(tarjetaDebitoIdCuentaPrincipalPatchRequest).then(Mono.empty());

    }

    @Override
    public  Mono<ResponseEntity<Void>> tarjetaDebitoIdDelete(String id,  final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }

    @Override
    public  Mono<ResponseEntity<Void>> tarjetaDebitoIdGet(String id,  final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }

    @Override
    public  Mono<ResponseEntity<Void>> tarjetaDebitoPost(Mono<TarjetaDebitoRequest> tarjetaDebitoRequest,
                                                         final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(tarjetaDebitoRequest).then(Mono.empty());

    }
}
