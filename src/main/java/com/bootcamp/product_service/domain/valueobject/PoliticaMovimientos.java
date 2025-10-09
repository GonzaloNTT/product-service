package com.bootcamp.product_service.domain.valueobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class PoliticaMovimientos {

    private final int maximoSinComision;

    public PoliticaMovimientos(int maximoSinComision) {
        if (maximoSinComision < 0) {
            throw new IllegalArgumentException("El máximo de movimientos no puede ser negativo");
        }
        this.maximoSinComision = maximoSinComision;
    }
}