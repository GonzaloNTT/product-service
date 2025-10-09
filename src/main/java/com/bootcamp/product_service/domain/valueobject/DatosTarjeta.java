package com.bootcamp.product_service.domain.valueobject;

import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class DatosTarjeta {

    private final String numero;
    private final LocalDate fechaExpiracion;
    private final String cvv;

    private DatosTarjeta(String numero, LocalDate fechaExpiracion, String cvv) {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("El número de tarjeta no puede ser nulo ni vacío");
        }
        if (fechaExpiracion == null) {
            throw new IllegalArgumentException("La fecha de expiración no puede ser nula");
        }
        if (cvv == null || cvv.isBlank()) {
            throw new IllegalArgumentException("El CVV no puede ser nulo ni vacío");
        }
        this.numero = numero;
        this.fechaExpiracion = fechaExpiracion;
        this.cvv = cvv;
    }
}