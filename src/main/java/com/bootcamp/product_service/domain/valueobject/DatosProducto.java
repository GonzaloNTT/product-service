package com.bootcamp.product_service.domain.valueobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
public class DatosProducto {

    private final LocalDateTime fechaCreacion;

    public DatosProducto() {
        this.fechaCreacion = LocalDateTime.now();
    }
}