package com.bootcamp.product_service.domain.aggregate.accounts;

import com.bootcamp.product_service.domain.enums.TipoCuentaAhorro;
import com.bootcamp.product_service.domain.enums.TipoMoneda;
import com.bootcamp.product_service.domain.valueobject.Dinero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaAhorroTest {

    private CuentaAhorro cuenta;

    @BeforeEach
    void setUp() {
        // Arrange
        cuenta = new CuentaAhorro("cliente-123", TipoCuentaAhorro.NORMAL);
    }

    @Test
    @DisplayName("Al crear una cuenta de ahorro se debe inicializar el saldo en cero")
    void crearCuentaAhorroDebeInicializarSaldoEnCero() {
        // Act -> (no hay acción adicional, se usa la inicialización en setUp)

        // Assert
        assertNotNull(cuenta.getId());
        assertEquals("cliente-123", cuenta.getClienteId());
        assertEquals(BigDecimal.ZERO, cuenta.getSaldo().getMonto());
        assertEquals(TipoMoneda.SOLES, cuenta.getSaldo().getMoneda());
    }

    @ParameterizedTest(name = "Depositar {0} soles debe aumentar el saldo correctamente")
    @ValueSource(longs = {50, 200, 1000})
    @DisplayName("Depositar montos válidos aumenta el saldo")
    void depositarMontoValidoDebeAumentarSaldo(long monto) {
        // Arrange
        Dinero deposito = new Dinero(BigDecimal.valueOf(monto), TipoMoneda.SOLES);

        // Act
        cuenta.depositar(deposito);

        // Assert
        assertEquals(BigDecimal.valueOf(monto), cuenta.getSaldo().getMonto());
    }

    @ParameterizedTest(name = "Depositar {0} soles (monto negativo) debe lanzar excepción")
    @ValueSource(longs = {-1, -100, -999})
    @DisplayName("Depositar montos negativos lanza excepción")
    void depositarMontoNegativoDebeLanzarExcepcion(long montoNegativo) {
        // Arrange
        Dinero deposito = new Dinero(BigDecimal.valueOf(montoNegativo), TipoMoneda.SOLES);

        // Act + Assert
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> cuenta.depositar(deposito)
        );

        assertEquals("El monto a depositar debe ser mayor a cero", ex.getMessage());
    }

    @ParameterizedTest(name = "Crear cuenta con clienteId={0} debe lanzar excepción")
    @CsvSource({
            " ,VIP",   // null (simulado con cadena vacía en CSV, luego se limpia)
            " ,NORMAL"
    })
    @DisplayName("Crear cuenta con clienteId nulo o vacío lanza excepción")
    void crearCuentaConClienteIdInvalidoDebeLanzarExcepcion(String clienteId, TipoCuentaAhorro tipoCuenta) {
        // Arrange + Act + Assert
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CuentaAhorro(clienteId == null || clienteId.isBlank() ? null : clienteId, tipoCuenta)
        );

        assertEquals("El cliente no puede ser nulo", ex.getMessage());
    }
}