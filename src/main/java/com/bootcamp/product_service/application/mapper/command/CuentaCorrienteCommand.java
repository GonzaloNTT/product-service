package com.bootcamp.product_service.application.mapper.command;

import com.bootcamp.product_service.domain.enums.TipoCuentaCorriente;

public record CuentaCorrienteCommand(
        String clienteId,
        TipoCuentaCorriente tipoCuentaCorriente,
        String moneda
) {}

//Crear cuenta corriente
//Elininar cuenta corriente
//Obtener cuenta corriente por id cliente