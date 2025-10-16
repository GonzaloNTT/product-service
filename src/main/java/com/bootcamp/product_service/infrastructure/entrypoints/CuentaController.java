package com.bootcamp.product_service.infrastructure.entrypoints;

import com.bootcamp.product_service.application.mapper.CommandMapper;
import com.bootcamp.product_service.application.mapper.command.CreditoCommand;
import com.bootcamp.product_service.application.mapper.command.CuentaAhorroCommand;
import com.bootcamp.product_service.domain.aggregate.accounts.CuentaAhorro;
import com.bootcamp.product_service.domain.aggregate.accounts.CuentaCorriente;
import com.bootcamp.product_service.domain.service.accounts.CuentaAhorroService;
import com.bootcamp.product_service.domain.service.accounts.CuentaCorrienteService;
import com.bootcamp.product_service.domain.service.accounts.CuentaPlazoFijoService;
import com.bootcamp.product_service.domain.service.credit.CreditoService;
import com.bootcamp.product_service.server.CreditoApi;
import com.bootcamp.product_service.server.CuentaApi;
import com.bootcamp.product_service.server.models.CuentaAhorroRequest;
import com.bootcamp.product_service.server.models.CuentaCorrienteRequest;
import com.bootcamp.product_service.server.models.CuentaPlazoFijoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@Slf4j
public class CuentaController implements CuentaApi {
    private final CuentaAhorroService cuentaAhorroService;
    private final CuentaCorrienteService cuentaCorrienteService;
    private final CuentaPlazoFijoService cuentaPlazoFijoService;

    public CuentaController(CuentaAhorroService cuentaAhorroService, CuentaCorrienteService cuentaCorrienteService,
                            CuentaPlazoFijoService cuentaPlazoFijoService) {
        this.cuentaAhorroService = cuentaAhorroService;
        this.cuentaCorrienteService = cuentaCorrienteService;
        this.cuentaPlazoFijoService = cuentaPlazoFijoService;
    }

    @Override
    public Mono<ResponseEntity<Void>> cuentaAhorroClienteClienteIdGet(String clienteId,
                                                                      final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }

    @Override
    public  Mono<ResponseEntity<Void>> cuentaAhorroIdDelete(String id,  final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }

    @Override
    public  Mono<ResponseEntity<Void>> cuentaAhorroPost(Mono<CuentaAhorroRequest> cuentaAhorroRequest,
                                                        final ServerWebExchange exchange) {
        return cuentaAhorroRequest
                .flatMap(request -> {
                    // Mapear request a command
                    CuentaAhorroCommand command = CommandMapper.cuentaAhorroMapper.apply(request);

                    // Llamar al servicio
                    return cuentaAhorroService.create(command)
                            .map(cuentaAhorro -> {
                                return ResponseEntity
                                        .created(URI.create("/api/v1/cuenta/ahorro" + cuentaAhorro.getId()))
                                        .<Void>build();
                            });
                })
                .onErrorResume(ex -> {
                    log.error("Error creando cuenta de ahorro", ex);
                    return Mono.just(ResponseEntity
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .<Void>build());
                });

    }

    @Override
    public  Mono<ResponseEntity<Void>> cuentaCorrienteClienteClienteIdGet(String clienteId,
                                                                          final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }

    @Override
    public  Mono<ResponseEntity<Void>> cuentaCorrienteIdDelete(String id,  final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }

    @Override
    public  Mono<ResponseEntity<Void>> cuentaCorrientePost(Mono<CuentaCorrienteRequest> cuentaCorrienteRequest,
                                                           final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(cuentaCorrienteRequest).then(Mono.empty());

    }

    @Override
    public  Mono<ResponseEntity<Void>> cuentaPlazoFijoClienteClienteIdGet(String clienteId,
                                                                          final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }

    @Override
    public  Mono<ResponseEntity<Void>> cuentaPlazoFijoIdDelete(String id,  final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }

    @Override
    public  Mono<ResponseEntity<Void>> cuentaPlazoFijoPost(Mono<CuentaPlazoFijoRequest> cuentaPlazoFijoRequest,
                                                           final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(cuentaPlazoFijoRequest).then(Mono.empty());

    }
}
