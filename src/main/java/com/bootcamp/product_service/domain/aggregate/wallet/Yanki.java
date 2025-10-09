package com.bootcamp.product_service.domain.aggregate.wallet;

import com.bootcamp.product_service.domain.valueobject.DatosProducto;
import com.bootcamp.product_service.domain.valueobject.NoClienteId;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class Yanki {

    private final String id;
    private final DatosProducto datosProducto;

    private final String clienteId;
    private final NoClienteId noClienteId;

    private final String tarjetaDebitoAsociada;

    private Yanki(String id,
                  DatosProducto datosProducto,
                  String clienteId,
                  NoClienteId noClienteId,
                  String tarjetaDebitoAsociada) {

        if (clienteId == null && noClienteId == null) {
            throw new IllegalArgumentException("Debe existir un cliente o un no cliente");
        }
        if (tarjetaDebitoAsociada == null) {
            throw new IllegalArgumentException("El Yanki debe estar asociado a una tarjeta de débito");
        }

        this.id = id;
        this.datosProducto = datosProducto;
        this.clienteId = clienteId;
        this.noClienteId = noClienteId;
        this.tarjetaDebitoAsociada = tarjetaDebitoAsociada;
    }

    public static Yanki crearParaCliente(String clienteId, String tarjetaDebitoId) {
        return new Yanki(new String(UUID.randomUUID().toString()), new DatosProducto(), clienteId,
                null, tarjetaDebitoId);
    }

    public static Yanki crearParaNoCliente(NoClienteId noClienteId, String tarjetaDebitoId) {
        return new Yanki(new String(UUID.randomUUID().toString()), new DatosProducto(),
                null, noClienteId, tarjetaDebitoId);
    }
}