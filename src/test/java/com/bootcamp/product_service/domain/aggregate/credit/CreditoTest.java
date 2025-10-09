package com.bootcamp.product_service.domain.aggregate.credit;

import com.bootcamp.product_service.domain.enums.TipoCredito;
import com.bootcamp.product_service.domain.enums.TipoMoneda;
import com.bootcamp.product_service.domain.valueobject.DatosProducto;
import com.bootcamp.product_service.domain.valueobject.Dinero;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CreditoTest {

    @Test
    void crearCreditoDebeInicializarSaldoPendienteIgualAlMontoOriginal() {
        Dinero monto = new Dinero(BigDecimal.valueOf(1000), TipoMoneda.SOLES);

        Credito credito = Credito.crear("cliente-1", TipoCredito.PERSONAL, monto, 12);

        assertEquals(monto.getMonto(), credito.getSaldoPendiente().getMonto());
        assertEquals(12, credito.getPlazo());
        assertEquals(TipoCredito.PERSONAL, credito.getTipo());
    }

    @Test
    void pagarCuotaReduceSaldoPendiente() {
        Dinero monto = new Dinero(BigDecimal.valueOf(1000), TipoMoneda.SOLES);
        Credito credito = Credito.crear("cliente-1", TipoCredito.PERSONAL, monto, 24);

        Dinero pago = new Dinero(BigDecimal.valueOf(200), TipoMoneda.SOLES);
        credito.pagarCuota(pago);

        assertEquals(BigDecimal.valueOf(800), credito.getSaldoPendiente().getMonto());
    }

    @Test
    void pagarCuotaMayorAlSaldoDebeLanzarExcepcion() {
        Dinero monto = new Dinero(BigDecimal.valueOf(500), TipoMoneda.SOLES);
        Credito credito = Credito.crear("cliente-1", TipoCredito.PERSONAL, monto, 6);

        Dinero pago = new Dinero(BigDecimal.valueOf(600), TipoMoneda.SOLES);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> credito.pagarCuota(pago)
        );

        assertEquals("El pago excede el saldo pendiente", ex.getMessage());
    }

    @Test
    void crearCreditoConPlazoInvalidoDebeLanzarExcepcion() {
        Dinero monto = new Dinero(BigDecimal.valueOf(1000), TipoMoneda.SOLES);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Credito("id1", "cliente-1", new DatosProducto(), TipoCredito.PERSONAL, monto, 0)
        );

        assertEquals("El plazo debe ser mayor a cero", ex.getMessage());
    }

    @Test
    void crearCreditoConClienteNuloDebeLanzarExcepcion() {
        Dinero monto = new Dinero(BigDecimal.valueOf(1000), TipoMoneda.SOLES);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Credito("id1", null, new DatosProducto(), TipoCredito.PERSONAL, monto, 10)
        );

        assertEquals("El cliente no puede ser nulo", ex.getMessage());
    }
}