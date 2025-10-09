package com.bootcamp.product_service.domain.aggregate.accounts;

import com.bootcamp.product_service.domain.enums.TipoCuentaAhorro;
import com.bootcamp.product_service.domain.enums.TipoMoneda;
import com.bootcamp.product_service.domain.valueobject.Dinero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaAhorroTest {

    private CuentaAhorro cuenta;

    @BeforeEach
    void setUp() {
        cuenta = new CuentaAhorro(
                "cliente-123",
                TipoCuentaAhorro.NORMAL
        );
    }

    @Test
    void crearCuentaAhorroDebeInicializarSaldoEnCero() {
        assertNotNull(cuenta.getId());
        assertEquals("cliente-123", cuenta.getClienteId());
        assertEquals(BigDecimal.ZERO, cuenta.getSaldo().getMonto());
        assertEquals(TipoMoneda.SOLES, cuenta.getSaldo().getMoneda());
    }

    @Test
    void depositarMontoValidoDebeAumentarSaldo() {
        Dinero monto = new Dinero(BigDecimal.valueOf(200), TipoMoneda.SOLES);
        cuenta.depositar(monto);

        assertEquals(BigDecimal.valueOf(200), cuenta.getSaldo().getMonto());
    }

    @Test
    void depositarMontoNegativoDebeLanzarExcepcion() {
        Dinero montoNegativo = new Dinero(BigDecimal.valueOf(-100), TipoMoneda.SOLES);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> cuenta.depositar(montoNegativo)
        );

        assertEquals("El monto a depositar debe ser mayor a cero", ex.getMessage());
    }

    @Test
    void crearCuentaConClienteIdNuloDebeLanzarExcepcion() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CuentaAhorro(null, TipoCuentaAhorro.VIP)
        );

        assertEquals("El cliente no puede ser nulo", ex.getMessage());
    }
}