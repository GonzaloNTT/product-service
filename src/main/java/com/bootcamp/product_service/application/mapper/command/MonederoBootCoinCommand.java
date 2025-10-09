package com.bootcamp.product_service.application.mapper.command;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MonederoBootCoinCommand(
        String clienteId,
        String noClienteId,
        String cuentaAsociadaId
) {}

//crear monedero
//Eliminar monedero
//Obtener monederoporIDcliente