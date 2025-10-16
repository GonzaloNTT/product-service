package com.bootcamp.product_service.application.mapper;
import com.bootcamp.product_service.application.mapper.command.*;
import com.bootcamp.product_service.domain.enums.TipoCredito;
import com.bootcamp.product_service.domain.enums.TipoCuentaAhorro;
import com.bootcamp.product_service.server.models.*;
import java.util.function.Function;

import java.math.BigDecimal;

public class CommandMapper {

    private CommandMapper() {}

    public static final Function<YankiClienteRequest, YankiCommand> yankiClienteMapper =
            req -> new YankiCommand(
                    req.getClienteId(),
                    null,
                    null,
                    req.getTelefono(),
                    req.getImei(),
                    req.getCorreo()
            );

    public static final Function<YankiNoClienteRequest, YankiCommand> yankiNoClienteMapper =
            req -> new YankiCommand(
                    req.getClienteId(),
                    req.getTipoDocumento(),
                    req.getNumeroDocumento(),
                    req.getTelefono(),
                    req.getImei(),
                    req.getCorreo()
            );

    public static final Function<TarjetaDebitoRequest, TarjetaDebitoCommand> tarjetaDebitoMapper =
            req -> new TarjetaDebitoCommand(
                    req.getClienteId(),
                    req.getMoneda(),
                    req.getString()
            );

    public static final Function<TarjetaDebitoIdCuentaPrincipalPatchRequest, TarjetaDebitoCommand> tarjetaDebitoPatchMapper =
            req -> new TarjetaDebitoCommand(
                    null,
                    null,
                    req.getString()
            );

    // ---- Tarjeta Crédito ----
    public static final Function<TarjetaCreditoRequest, TarjetaCreditoCommand> tarjetaCreditoMapper =
            req -> new TarjetaCreditoCommand(
                    req.getClienteId(),
                    null,
                    req.getMoneda(),
                    req.getLimiteCredito()
            );

    public static final Function<MonederoBootCoinRequest, MonederoBootCoinCommand> monederoBootCoinMapper =
            req -> new MonederoBootCoinCommand(
                    req.getClienteId(),
                    req.getNoClienteId(),
                    req.getCuentaAsociadaId()
            );

    public static final Function<CuentaPlazoFijoRequest, CuentaPlazoFijoCommand> cuentaPlazoFijoMapper =
            req -> new CuentaPlazoFijoCommand(
                    req.getClienteId(),
                    req.getMoneda()
            );

    public static final Function<CuentaCorrienteRequest, CuentaCorrienteCommand> cuentaCorrienteMapper =
            req -> new CuentaCorrienteCommand(
                    req.getClienteId(),
                    null,
                    req.getMoneda()
            );

    public static final Function<CuentaAhorroRequest, CuentaAhorroCommand> cuentaAhorroMapper =
            req -> new CuentaAhorroCommand(
                    req.getClienteId(),
                    TipoCuentaAhorro.valueOf(req.getTipoCuentaAhorro().name()),
                    req.getMoneda()
            );

    public static final Function<CreditoRequest, CreditoCommand> creditoMapper =
            req -> new CreditoCommand(
                    req.getClienteId(),
                    req.getMonto() != null ? BigDecimal.valueOf(req.getMonto()) : BigDecimal.ZERO,
                    req.getMoneda(),
                    TipoCredito.valueOf(req.getTipoCredito().getValue()),
                    req.getPlazo()
            );
}
