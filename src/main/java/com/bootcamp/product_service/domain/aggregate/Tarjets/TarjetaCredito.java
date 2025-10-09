package com.bootcamp.product_service.domain.aggregate.Tarjets;

import com.bootcamp.product_service.domain.entity.ConsumoTarjetaCredito;
import com.bootcamp.product_service.domain.enums.TipoMoneda;
import com.bootcamp.product_service.domain.enums.TipoProductoBancario;
import com.bootcamp.product_service.domain.enums.TipoTarjeta;

import com.bootcamp.product_service.domain.valueobject.DatosProducto;
import com.bootcamp.product_service.domain.valueobject.Dinero;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@EqualsAndHashCode

public class TarjetaCredito {

    private String id;
    private String clienteId;
    private DatosProducto datosProducto;
    private final TipoTarjeta tipo = TipoTarjeta.CREDITO;
    private final TipoProductoBancario tipoProducto = TipoProductoBancario.ACTIVO;

    private Dinero limiteCredito;
    private Dinero saldoDisponible;
    private List<ConsumoTarjetaCredito> consumos;

    public TarjetaCredito() {}

    public TarjetaCredito(String clienteId,
                           DatosProducto datosProducto,
                           Dinero limiteCredito) {
        this(UUID.randomUUID().toString(), clienteId, datosProducto, limiteCredito);
    }

    public TarjetaCredito(String id,
                          String clienteId,
                          DatosProducto datosProducto,
                          Dinero limiteCredito) {

        if (id == null) throw new IllegalArgumentException("El id de tarjeta no puede ser nulo");
        if (clienteId == null) throw new IllegalArgumentException("El cliente no puede ser nulo");
        if (limiteCredito == null) throw new IllegalArgumentException("Debe definir un límite de crédito");

        this.id = id;
        this.clienteId = clienteId;
        this.datosProducto = datosProducto;
        this.limiteCredito = limiteCredito;
        this.saldoDisponible = limiteCredito;
        this.consumos = new ArrayList<>();
    }

    public void registrarConsumo(ConsumoTarjetaCredito consumo) {
        if (consumo.getMonto().getMonto().compareTo(saldoDisponible.getMonto()) > 0) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar el consumo");
        }
        this.saldoDisponible = this.saldoDisponible.reducirMonto(
                new Dinero(consumo.getMonto().getMonto(), saldoDisponible.getMoneda()));
        this.consumos.add(consumo);
    }

    public void pagar(Dinero monto) {
        this.saldoDisponible = this.saldoDisponible.aumentarMonto(monto);
        if (saldoDisponible.getMonto().compareTo(limiteCredito.getMonto()) > 0) {
            this.saldoDisponible = limiteCredito;
        }
    }
}