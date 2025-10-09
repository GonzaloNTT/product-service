package com.bootcamp.product_service.domain.entity;

import com.bootcamp.product_service.domain.valueobject.Dinero;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
public class Cuota {

    private final int numero;
    private final LocalDate fechaVencimiento;
    private final Dinero monto;
    private boolean pagada;

    private Cuota(int numero, LocalDate fechaVencimiento, Dinero monto) {
        if (numero <= 0) {
            throw new IllegalArgumentException("El número de cuota debe ser mayor a cero");
        }
        if (fechaVencimiento == null) {
            throw new IllegalArgumentException("La fecha de vencimiento no puede ser nula");
        }
        if (monto == null) {
            throw new IllegalArgumentException("El monto no puede ser nulo");
        }

        this.numero = numero;
        this.fechaVencimiento = fechaVencimiento;
        this.monto = monto;
        this.pagada = false;
    }

    public static Cuota crear(int numero, LocalDate fechaVencimiento, Dinero monto) {
        return new Cuota(numero, fechaVencimiento, monto);
    }

    public void marcarComoPagada() {
        this.pagada = true;
    }
}