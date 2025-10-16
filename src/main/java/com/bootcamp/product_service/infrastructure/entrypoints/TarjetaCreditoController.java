package com.bootcamp.product_service.infrastructure.entrypoints;

import com.bootcamp.product_service.application.mapper.CommandMapper;
import com.bootcamp.product_service.application.mapper.command.CreditoCommand;
import com.bootcamp.product_service.application.mapper.command.TarjetaCreditoCommand;
import com.bootcamp.product_service.domain.service.tarjets.TarjetaCreditoService;
import com.bootcamp.product_service.server.CuentaApi;
import com.bootcamp.product_service.server.TarjetaCreditoApi;
import com.bootcamp.product_service.server.models.TarjetaCreditoRequest;
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
public class TarjetaCreditoController implements TarjetaCreditoApi {

    private final TarjetaCreditoService service;
    public TarjetaCreditoController(TarjetaCreditoService service) {
        this.service = service;
    }


    @Override
    public  Mono<ResponseEntity<Void>> tarjetaCreditoClienteClienteIdGet(String clienteId,
                                                                         final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }

    @Override
    public  Mono<ResponseEntity<Void>> tarjetaCreditoIdDelete(String id,  final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }

    @Override
    public  Mono<ResponseEntity<Void>> tarjetaCreditoPost(Mono<TarjetaCreditoRequest> tarjetaCreditoRequest,
                                                          final ServerWebExchange exchange) {
        return tarjetaCreditoRequest
                .flatMap(request -> {
                    // Mapear request a command
                    TarjetaCreditoCommand command = CommandMapper.tarjetaCreditoMapper.apply(request);

                    // Llamar al servicio
                    return service.create(command)
                            .map(tarjetaCredito -> {
                                // Retornar Created con Location header
                                return ResponseEntity
                                        .created(URI.create("/api/v1/tarjeta-credito/" + tarjetaCredito.getId()))
                                        .<Void>build();
                            });
                })
                .onErrorResume(ex -> {
                    log.error("Error creando tarjeta de crédito", ex);
                    return Mono.just(ResponseEntity
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .<Void>build());
                });

    }
}
