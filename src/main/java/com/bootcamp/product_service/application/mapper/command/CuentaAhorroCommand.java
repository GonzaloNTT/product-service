package com.bootcamp.product_service.application.mapper.command;

import com.bootcamp.product_service.domain.enums.TipoCuentaAhorro;

public record CuentaAhorroCommand(
        String clienteId,
        TipoCuentaAhorro tipoCuentaAhorro,
        String moneda
) {}

//crear cuenta ahorro
//eliminar cuenta ahorro
//obtener cuenta ahooro por id cliente