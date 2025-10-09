package com.bootcamp.product_service.application.mapper.command;

import com.bootcamp.product_service.domain.enums.TipoCredito;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreditoCommand(
        String clienteId,
        BigDecimal monto,
        String moneda,          // Ej: "SOLES" o "DOLARES"
        TipoCredito tipoCredito,
        Integer plazo
) {}

//Crear credito
//Obtener credito por id