package com.bootcamp.product_service.domain.entity;

import com.bootcamp.product_service.domain.valueobject.Dinero;
import lombok.Getter;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@EqualsAndHashCode
public class ConsumoTarjetaCredito {

    private String referencia;
    private Dinero monto;
    private LocalDateTime fecha;

    public ConsumoTarjetaCredito(){}

    public ConsumoTarjetaCredito(String referencia, Dinero monto) {
        if (referencia == null || referencia.isBlank()) {
            throw new IllegalArgumentException("La referencia no puede ser nula ni vacía");
        }
        if (monto == null) {
            throw new IllegalArgumentException("El monto no puede ser nulo");
        }

        this.referencia = referencia;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
    }

    public static ConsumoTarjetaCredito crear(String referencia, Dinero monto) {
        return new ConsumoTarjetaCredito(referencia, monto);
    }

    public static ConsumoTarjetaCredito crearAutomatico(Dinero monto) {
        return new ConsumoTarjetaCredito(UUID.randomUUID().toString(), monto);
    }
}