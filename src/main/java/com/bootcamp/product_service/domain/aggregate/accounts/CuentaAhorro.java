package com.bootcamp.product_service.domain.aggregate.accounts;

import com.bootcamp.product_service.domain.enums.TipoCuentaAhorro;
import com.bootcamp.product_service.domain.enums.TipoMoneda;
import com.bootcamp.product_service.domain.enums.TipoProductoBancario;
import com.bootcamp.product_service.domain.valueobject.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@EqualsAndHashCode
public class CuentaAhorro {

    private final String id;
    private final String clienteId;
    private final DatosProducto datosProducto;
    private final PoliticaMovimientos politicaMovimientos;
    private final PoliticaComision politicaComision;
    private final TipoCuentaAhorro tipo;
    private final TipoProductoBancario tipoProducto = TipoProductoBancario.PASIVO;

    private Dinero saldo;

    public CuentaAhorro(String id,
                        String clienteId,
                        DatosProducto datosProducto,
                        PoliticaMovimientos politicaMovimientos,
                        PoliticaComision politicaComision,
                        TipoCuentaAhorro tipo,
                        Dinero saldoInicial) {

        if (clienteId == null) throw new IllegalArgumentException("El cliente no puede ser nulo");
        if (id == null) throw new IllegalArgumentException("El cliente id no puede ser nulo");

        this.id = id;
        this.clienteId = clienteId;
        this.datosProducto = datosProducto;
        this.politicaMovimientos = politicaMovimientos;
        this.politicaComision = politicaComision;
        this.tipo = tipo;
        this.saldo = saldoInicial;
    }

    public CuentaAhorro(String clienteId, TipoCuentaAhorro tipo) {
        this(
                UUID.randomUUID().toString(), // genera un ID único
                clienteId,
                new DatosProducto(),
                new PoliticaMovimientos(360),
                new PoliticaComision(new Dinero(BigDecimal.ZERO, TipoMoneda.SOLES)),
                tipo,
                new Dinero(BigDecimal.ZERO, TipoMoneda.SOLES)
        );
    }

    public void depositar(Dinero monto) {
        if (monto.getMonto().compareTo( BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor a cero");
        }
        this.saldo = this.saldo.aumentarMonto(monto);
    }
}