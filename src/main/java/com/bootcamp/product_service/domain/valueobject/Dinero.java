package com.bootcamp.product_service.domain.valueobject;

import com.bootcamp.product_service.domain.enums.TipoMoneda;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode

public class Dinero {

    private BigDecimal monto;
    private final TipoMoneda moneda;

    public Dinero(BigDecimal monto, TipoMoneda moneda) {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El monto no puede ser nulo ni negativo");
        }
        if (moneda == null) {
            throw new IllegalArgumentException("La moneda no puede ser nula");
        }
        this.monto = monto;
        this.moneda = moneda;
    }

    public Dinero aumentarMonto(Dinero monto) {
        if (this.getMoneda() != monto.getMoneda()) {
            throw new IllegalArgumentException("Las monedas deben ser iguales para realizar la operación");
        }
        return new Dinero(this.monto.add(monto.getMonto()), this.moneda);
    }

    public Dinero reducirMonto(Dinero monto) {
        if (this.getMoneda() != monto.getMoneda()) {
            throw new IllegalArgumentException("Las monedas deben ser iguales para realizar la operación");
        }
        BigDecimal nuevoMonto = this.monto.subtract(monto.getMonto());
        if (nuevoMonto.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El monto no puede ser negativo");
        }
        return new Dinero(nuevoMonto, this.moneda);
    }
}