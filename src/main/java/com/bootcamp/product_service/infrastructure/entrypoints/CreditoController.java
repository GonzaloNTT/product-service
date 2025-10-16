package com.bootcamp.product_service.infrastructure.entrypoints;

import com.bootcamp.product_service.application.mapper.CommandMapper;
import com.bootcamp.product_service.application.mapper.command.CreditoCommand;
import com.bootcamp.product_service.application.mapper.command.TarjetaCreditoCommand;
import com.bootcamp.product_service.domain.service.credit.CreditoService;
import com.bootcamp.product_service.server.CreditoApi;
import com.bootcamp.product_service.server.models.CreditoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@Slf4j
public class CreditoController implements CreditoApi {
    private final CreditoService service;

    public CreditoController(CreditoService service) {
        this.service = service;
    }

    @Override
    public  Mono<ResponseEntity<Void>> creditoIdGet(String id,  final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }
    @Override
    public Mono<ResponseEntity<Void>> creditoPost(Mono<CreditoRequest> creditoRequest,
                                                  final ServerWebExchange exchange) {
        return creditoRequest
                .flatMap(request -> {
                    // Mapear request a command
                    CreditoCommand command = CommandMapper.creditoMapper.apply(request);

                    // Llamar al servicio
                    return service.create(command)
                            .map(cuentaAhorro -> {
                                // Retornar Created con Location header
                                return ResponseEntity
                                        .created(URI.create("/api/v1/credito/" + cuentaAhorro.getId()))
                                        .<Void>build();
                            });
                })
                .onErrorResume(ex -> {
                    log.error("Error creando crédito", ex);
                    return Mono.just(ResponseEntity
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .<Void>build());
                });
    }
}
