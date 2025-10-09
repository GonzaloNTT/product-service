package com.bootcamp.product_service.domain.aggregate.accounts;

import com.bootcamp.product_service.domain.enums.TipoCuentaCorriente;
import com.bootcamp.product_service.domain.enums.TipoProductoBancario;
import com.bootcamp.product_service.domain.valueobject.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class CuentaCorriente {

    private final String id;
    private final String clienteId;
    private final DatosProducto datosProducto;
    private final PoliticaMovimientos politicaMovimientos;
    private final PoliticaComision politicaComision;
    private final TipoCuentaCorriente tipo;
    private final TipoProductoBancario tipoProducto = TipoProductoBancario.PASIVO;

    private Dinero saldo;

    private CuentaCorriente(String id,
                            String clienteId,
                            DatosProducto datosProducto,
                            PoliticaMovimientos politicaMovimientos,
                            PoliticaComision politicaComision,
                            TipoCuentaCorriente tipo,
                            Dinero saldoInicial) {

        if (clienteId == null) throw new IllegalArgumentException("El cliente no puede ser nulo");

        this.id = id;
        this.clienteId = clienteId;
        this.datosProducto = datosProducto;
        this.politicaMovimientos = politicaMovimientos;
        this.politicaComision = politicaComision;
        this.tipo = tipo;
        this.saldo = saldoInicial;
    }

    public static CuentaCorriente crear(String clienteId,
                                        DatosProducto datosProducto,
                                        PoliticaMovimientos politicaMovimientos,
                                        PoliticaComision politicaComision,
                                        TipoCuentaCorriente tipo,
                                        Dinero saldoInicial) {

        return new CuentaCorriente(new String(UUID.randomUUID().toString()),
                clienteId, datosProducto, politicaMovimientos, politicaComision, tipo, saldoInicial);
    }
}