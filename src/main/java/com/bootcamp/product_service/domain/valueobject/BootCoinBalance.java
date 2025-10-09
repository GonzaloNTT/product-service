package com.bootcamp.product_service.domain.valueobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class BootCoinBalance {

    private final double cantidad;

    private BootCoinBalance(double cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("El balance no puede ser negativo");
        }
        this.cantidad = cantidad;
    }
}