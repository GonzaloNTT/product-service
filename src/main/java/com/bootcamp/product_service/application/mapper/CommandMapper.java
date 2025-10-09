package com.bootcamp.product_service.application.mapper;
import com.bootcamp.product_service.application.mapper.command.*;
import com.bootcamp.product_service.domain.enums.TipoCredito;
import com.bootcamp.product_service.domain.enums.TipoCuentaAhorro;
import com.bootcamp.product_service.server.models.*;

import java.math.BigDecimal;

public class CommandMapper {

    private CommandMapper() {}

    public static YankiCommand toCommand(YankiClienteRequest request) {
        return new YankiCommand(
                request.getClienteId(),
                null,
                null,
                request.getTelefono(),
                request.getImei(),
                request.getCorreo()
        );
    }

    public static YankiCommand toCommand(YankiNoClienteRequest request) {
        return new YankiCommand(
                request.getClienteId(),
                request.getTipoDocumento(),
                request.getNumeroDocumento(),
                request.getTelefono(),
                request.getImei(),
                request.getCorreo()
        );
    }

    public static TarjetaDebitoCommand toCommand(TarjetaDebitoRequest request) {
        return new TarjetaDebitoCommand(
                request.getClienteId(),
                request.getMoneda(),
                request.getString()
        );
    }

    public static TarjetaDebitoCommand toCommand(TarjetaDebitoIdCuentaPrincipalPatchRequest request) {
        return new TarjetaDebitoCommand(
                null,
                null,
                request.getString()
        );
    }

    public static TarjetaCreditoCommand toCommand(TarjetaCreditoRequest request) {
        return new TarjetaCreditoCommand(
                request.getClienteId(),
                null,
                request.getMoneda(),
                request.getLimiteCredito()
        );
    }

    public static MonederoBootCoinCommand toCommand(MonederoBootCoinRequest request) {
        return new MonederoBootCoinCommand(
                request.getClienteId(),
                request.getNoClienteId(),
                request.getCuentaAsociadaId()
        );
    }

    public static CuentaPlazoFijoCommand toCommand(CuentaPlazoFijoRequest request) {
        return new CuentaPlazoFijoCommand(
                request.getClienteId(),
                request.getMoneda()
        );
    }

    public static CuentaCorrienteCommand toCommand(CuentaCorrienteRequest request) {
        return new CuentaCorrienteCommand(
                request.getClienteId(),
                null,
                request.getMoneda()
        );
    }

    public static CuentaAhorroCommand toCommand(CuentaAhorroRequest request) {
        return new CuentaAhorroCommand(
                request.getClienteId(),
                TipoCuentaAhorro.valueOf(request.getTipoCuentaAhorro().name()),
                request.getMoneda()
        );
    }

    public static CreditoCommand toCommand(CreditoRequest request) {
        return new CreditoCommand(
                request.getClienteId(),
                request.getMonto() != null ? BigDecimal.valueOf(request.getMonto()) : BigDecimal.ZERO,
                request.getMoneda(),
                TipoCredito.valueOf(request.getTipoCredito().getValue()),
                request.getPlazo()
        );
    }
}
