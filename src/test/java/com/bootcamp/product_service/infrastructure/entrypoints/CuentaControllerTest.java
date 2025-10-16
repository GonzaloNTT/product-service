package com.bootcamp.product_service.infrastructure.entrypoints;

import com.bootcamp.product_service.application.mapper.command.CuentaAhorroCommand;
import com.bootcamp.product_service.domain.aggregate.accounts.CuentaAhorro;
import com.bootcamp.product_service.domain.enums.TipoCuentaAhorro;
import com.bootcamp.product_service.domain.service.accounts.CuentaAhorroService;
import com.bootcamp.product_service.domain.service.accounts.CuentaCorrienteService;
import com.bootcamp.product_service.domain.service.accounts.CuentaPlazoFijoService;
import com.bootcamp.product_service.server.models.CuentaAhorroRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@WebFluxTest(CuentaController.class)
class CuentaControllerWebTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CuentaAhorroService cuentaAhorroService;

    @MockBean
    private CuentaCorrienteService cuentaCorrienteService;

    @MockBean
    private CuentaPlazoFijoService cuentaPlazoFijoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("✅ Debería retornar 201 (Created) cuando el servicio crea la cuenta correctamente")
    void cuentaAhorroPost_DebeRetornarCreatedCuandoExitoso() {
        CuentaAhorroRequest request = new CuentaAhorroRequest();
        request.setClienteId("cliente-123");
        request.setTipoCuentaAhorro(CuentaAhorroRequest.TipoCuentaAhorroEnum.NORMAL);
        request.setMoneda("SOLES");

        CuentaAhorro cuentaMock = new CuentaAhorro("cliente-123", TipoCuentaAhorro.NORMAL);

        when(cuentaAhorroService.create(any(CuentaAhorroCommand.class)))
                .thenReturn(Mono.just(cuentaMock));

        webTestClient.post()
                .uri("/api/v1/cuenta/ahorro")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().exists("Location");
    }

    @Test
    @DisplayName("❌ Debería retornar 500 (Internal Server Error) cuando el servicio lanza excepción")
    void cuentaAhorroPost_DebeRetornarErrorCuandoServicioFalla() {
        CuentaAhorroRequest request = new CuentaAhorroRequest();
        request.setClienteId("cliente-123");
        request.setTipoCuentaAhorro(CuentaAhorroRequest.TipoCuentaAhorroEnum.NORMAL);
        request.setMoneda("SOLES");

        when(cuentaAhorroService.create(any(CuentaAhorroCommand.class)))
                .thenReturn(Mono.error(new RuntimeException("Error en servicio")));

        webTestClient.post()
                .uri("/api/v1/cuenta/ahorro")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @ParameterizedTest(name = "Debe crear cuenta con tipo {0}")
    @EnumSource(CuentaAhorroRequest.TipoCuentaAhorroEnum.class)
    @DisplayName("🔁 Debería permitir creación con diferentes tipos de cuenta ahorro")
    void cuentaAhorroPost_DebeAceptarDiferentesTipos(CuentaAhorroRequest.TipoCuentaAhorroEnum tipoCuentaEnum) {
        CuentaAhorroRequest request = new CuentaAhorroRequest();
        request.setClienteId("cliente-123");
        request.setTipoCuentaAhorro(tipoCuentaEnum);
        request.setMoneda("SOLES");

        CuentaAhorro cuentaMock = new CuentaAhorro("cliente-123", TipoCuentaAhorro.valueOf(tipoCuentaEnum.name()));

        when(cuentaAhorroService.create(any(CuentaAhorroCommand.class)))
                .thenReturn(Mono.just(cuentaMock));

        webTestClient.post()
                .uri("/api/v1/cuenta/ahorro")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated();
    }
}