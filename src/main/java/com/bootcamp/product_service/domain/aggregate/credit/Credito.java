package com.bootcamp.product_service.domain.aggregate.credit;

import com.bootcamp.product_service.domain.enums.TipoCredito;
import com.bootcamp.product_service.domain.enums.TipoProductoBancario;
import com.bootcamp.product_service.domain.valueobject.DatosProducto;
import com.bootcamp.product_service.domain.valueobject.Dinero;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class Credito {

    private final String id;
    private final String clienteId;
    private final DatosProducto datosProducto;
    private final TipoCredito tipo;
    private final TipoProductoBancario tipoProducto = TipoProductoBancario.ACTIVO;
    private final Integer plazo;
    //TODO definri tasa interes y ganancias para el banco
    private final Double tasaInteres = 0.0;
    private final Dinero montoOriginal;
    private Dinero saldoPendiente;

    public Credito(String id,
                   String clienteId,
                   DatosProducto datosProducto,
                   TipoCredito tipo,
                   Dinero montoOriginal,
                   Integer plazo
    ) {

        if (id == null) UUID.randomUUID().toString();
        if (clienteId == null) throw new IllegalArgumentException("El cliente no puede ser nulo");
        if (montoOriginal == null) throw new IllegalArgumentException("El monto original no puede ser nulo");
        if (plazo <= 0) throw new IllegalArgumentException("El plazo debe ser mayor a cero");

        this.id = id;
        this.clienteId = clienteId;
        this.datosProducto = datosProducto;
        this.tipo = tipo;
        this.montoOriginal = montoOriginal;
        this.saldoPendiente = montoOriginal;
        this.plazo = plazo;
    }

    public static Credito crear(String clienteId,
                                TipoCredito tipo,
                                Dinero montoOriginal,
                                Integer plazo) {

        return new Credito(
                UUID.randomUUID().toString(),
                clienteId, new DatosProducto(), tipo, montoOriginal, plazo);
    }

    public void pagarCuota(Dinero monto) {
        if (monto.getMonto().compareTo(saldoPendiente.getMonto()) > 0) {
            throw new IllegalArgumentException("El pago excede el saldo pendiente");
        }
        this.saldoPendiente = this.saldoPendiente.reducirMonto(monto);
    }
}