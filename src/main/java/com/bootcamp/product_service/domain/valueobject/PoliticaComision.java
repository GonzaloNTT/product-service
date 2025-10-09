package com.bootcamp.product_service.domain.valueobject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class PoliticaComision {

    private final Dinero montoComision;

    public PoliticaComision(Dinero montoComision) {
        if (montoComision == null) {
            throw new IllegalArgumentException("La comisión no puede ser nula");
        }
        this.montoComision = montoComision;
    }
}