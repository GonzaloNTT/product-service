package com.bootcamp.product_service.domain.aggregate.wallet;

import com.bootcamp.product_service.domain.enums.TipoProductoBancario;
import com.bootcamp.product_service.domain.valueobject.DatosProducto;
import com.bootcamp.product_service.domain.valueobject.NoClienteId;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigInteger;

@Getter
@EqualsAndHashCode
public class MonederoBootCoin {
    private final String id;
    private final String clienteId;
    private final NoClienteId noClienteId;
    private DatosProducto datosProducto;
    private BigInteger cantidadBootCoin;
    private String cuentaAsociada; // Cuenta bancaria asociada
    private TipoProductoBancario tipoProductoBancario = TipoProductoBancario.BILLETERA;

    private MonederoBootCoin(String id, String clienteId, NoClienteId noClienteId,
                             DatosProducto datosProducto, BigInteger cantidadBootCoin, String cuentaAsociada) {
        this.id = id;
        this.noClienteId = noClienteId;
        this.datosProducto = datosProducto;
        if (cantidadBootCoin.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("La cantidad de BootCoin no puede ser negativa");
        }
        this.clienteId = clienteId;
        this.datosProducto = datosProducto;
        this.cantidadBootCoin = cantidadBootCoin;
        this.cuentaAsociada = cuentaAsociada;
    }

    public static MonederoBootCoin crear(String id, String clienteId, NoClienteId noClienteId,
                                         DatosProducto datosProducto, BigInteger cantidadBootCoin,
                                         String cuentaAsociada) {
        return new MonederoBootCoin(id, clienteId, noClienteId, datosProducto, cantidadBootCoin, cuentaAsociada);
    }

    public void agregarBootCoin(BigInteger cantidad) {
        if (cantidad.compareTo(BigInteger.ZERO) <= 0) {
            throw new IllegalArgumentException("La cantidad a agregar debe ser mayor a cero");
        }
        this.cantidadBootCoin = this.cantidadBootCoin.add(cantidad);
    }

    public void retirarBootCoin(BigInteger cantidad) {
        if (cantidad.compareTo(BigInteger.ZERO) <= 0) {
            throw new IllegalArgumentException("La cantidad a retirar debe ser mayor a cero");
        }
        if (this.cantidadBootCoin.compareTo(cantidad) < 0) {
            throw new IllegalArgumentException("No hay suficiente BootCoin en el monedero");
        }
        this.cantidadBootCoin = this.cantidadBootCoin.subtract(cantidad);
    }
}