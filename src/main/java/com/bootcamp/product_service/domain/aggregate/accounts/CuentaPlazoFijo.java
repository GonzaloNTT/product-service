package com.bootcamp.product_service.domain.aggregate.accounts;

import com.bootcamp.product_service.domain.enums.TipoProductoBancario;
import com.bootcamp.product_service.domain.valueobject.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class CuentaPlazoFijo {

    private final String id;
    private final String clienteId;
    private final DatosProducto datosProducto;
    private final PoliticaMovimientos politicaMovimientos;
    private final PoliticaComision politicaComision;
    private final TipoProductoBancario tipoProducto = TipoProductoBancario.PASIVO;

    private Dinero saldo;

    private CuentaPlazoFijo(String id,
                            String clienteId,
                            DatosProducto datosProducto,
                            PoliticaMovimientos politicaMovimientos,
                            PoliticaComision politicaComision,
                            Dinero saldoInicial) {

        if (id == null) throw new IllegalArgumentException("El cliente id no puede ser nulo");
        if (clienteId == null) throw new IllegalArgumentException("El cliente no puede ser nulo");
        if (saldoInicial == null) throw new IllegalArgumentException("El saldo inicial no puede ser nulo");

        this.id = id;
        this.clienteId = clienteId;
        this.datosProducto = datosProducto;
        this.politicaMovimientos = politicaMovimientos;
        this.politicaComision = politicaComision;
        this.saldo = saldoInicial;
    }

    public static CuentaPlazoFijo crear(String clienteId,
                                        PoliticaMovimientos politicaMovimientos,
                                        PoliticaComision politicaComision,
                                        Dinero saldoInicial) {

        return new CuentaPlazoFijo(new String(UUID.randomUUID().toString()), clienteId, new DatosProducto(),
                politicaMovimientos, politicaComision, saldoInicial);
    }
}
