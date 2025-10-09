package com.bootcamp.product_service.infrastructure.entrypoints;

import com.bootcamp.product_service.application.mapper.command.CuentaAhorroCommand;
import com.bootcamp.product_service.domain.aggregate.accounts.CuentaAhorro;
import com.bootcamp.product_service.domain.enums.TipoCuentaAhorro;
import com.bootcamp.product_service.domain.service.accounts.CuentaAhorroService;
import com.bootcamp.product_service.domain.service.accounts.CuentaCorrienteService;
import com.bootcamp.product_service.domain.service.accounts.CuentaPlazoFijoService;
import com.bootcamp.product_service.server.models.CuentaAhorroRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CuentaControllerTest {

    @Mock
    private CuentaAhorroService cuentaAhorroService;
    @Mock
    private CuentaCorrienteService cuentaCorrienteService;
    @Mock
    private CuentaPlazoFijoService cuentaPlazoFijoService;

    @InjectMocks
    private CuentaController cuentaController;

    @Mock
    private ServerWebExchange exchange;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ✅ Caso exitoso
    @Test
    void cuentaAhorroPost_deberiaRetornarCreatedCuandoServicioExitoso() {
        // Arrange
        CuentaAhorroRequest request = new CuentaAhorroRequest();
        request.setClienteId("cliente-123");
        request.setTipoCuentaAhorro(CuentaAhorroRequest.TipoCuentaAhorroEnum.NORMAL);
        request.setMoneda("SOLES");

        CuentaAhorro cuentaMock = new CuentaAhorro("cliente-123", TipoCuentaAhorro.NORMAL);

        when(cuentaAhorroService.create(any(CuentaAhorroCommand.class)))
                .thenReturn(Mono.just(cuentaMock));

        // Act
        Mono<ResponseEntity<Void>> responseMono = cuentaController.cuentaAhorroPost(Mono.just(request), exchange);

        // Assert
        StepVerifier.create(responseMono)
                .assertNext(response -> {
                    assertEquals(HttpStatus.CREATED, response.getStatusCode());
                    assertTrue(response.getHeaders().getLocation().toString()
                            .contains("/api/v1/cuenta/ahorro" + cuentaMock.getId()));
                })
                .verifyComplete();

        verify(cuentaAhorroService, times(1)).create(any(CuentaAhorroCommand.class));
    }

    // Caso de error
    @Test
    void cuentaAhorroPost_deberiaRetornarInternalServerErrorCuandoServicioFalla() {
        // Arrange
        CuentaAhorroRequest request = new CuentaAhorroRequest();
        request.setClienteId("cliente-123");
        request.setTipoCuentaAhorro(CuentaAhorroRequest.TipoCuentaAhorroEnum.NORMAL);
        request.setMoneda("SOLES");

        when(cuentaAhorroService.create(any(CuentaAhorroCommand.class)))
                .thenReturn(Mono.error(new RuntimeException("Error en servicio")));

        // Act
        Mono<ResponseEntity<Void>> responseMono = cuentaController.cuentaAhorroPost(Mono.just(request), exchange);

        // Assert
        StepVerifier.create(responseMono)
                .assertNext(response ->
                        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode()))
                .verifyComplete();

        verify(cuentaAhorroService, times(1)).create(any(CuentaAhorroCommand.class));
    }
}