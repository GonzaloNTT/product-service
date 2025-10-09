package com.bootcamp.product_service.domain.aggregate.Tarjets;

import com.bootcamp.product_service.domain.enums.TipoProductoBancario;
import com.bootcamp.product_service.domain.enums.TipoTarjeta;
import com.bootcamp.product_service.domain.valueobject.DatosProducto;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@EqualsAndHashCode
public class TarjetaDebito {

    private final String id;
    private final String clienteId;
    private final DatosProducto datosProducto;
    private final TipoTarjeta tipo = TipoTarjeta.DEBITO;
    private final TipoProductoBancario tipoProducto = TipoProductoBancario.PASIVO;

    // Una tarjeta de débito puede estar asociada a varias cuentas, pero una es principal
    private final Set<String> cuentasAsociadas = new HashSet<>();
    private String cuentaPrincipal;

    private TarjetaDebito(String id,
                          String clienteId,
                          DatosProducto datosProducto,
                          String cuentaPrincipal) {

        if (id == null) throw new IllegalArgumentException("El id de tarjeta no puede ser nulo");
        if (clienteId == null) throw new IllegalArgumentException("El cliente no puede ser nulo");
        if (cuentaPrincipal == null) throw new IllegalArgumentException("Debe definir una cuenta principal");

        this.id = id;
        this.clienteId = clienteId;
        this.datosProducto = datosProducto;
        this.cuentaPrincipal = cuentaPrincipal;
        this.cuentasAsociadas.add(cuentaPrincipal);
    }

    public static TarjetaDebito crear(String clienteId, String cuentaPrincipal) {
        return new TarjetaDebito(new String(UUID.randomUUID().toString()), clienteId,
                new DatosProducto(), cuentaPrincipal);
    }

    public void asociarCuenta(String String) {
        this.cuentasAsociadas.add(String);
    }

    public void cambiarCuentaPrincipal(String nuevaPrincipal) {
        if (!cuentasAsociadas.contains(nuevaPrincipal)) {
            throw new IllegalArgumentException("La cuenta debe estar previamente asociada");
        }
        this.cuentaPrincipal = nuevaPrincipal;
    }
}