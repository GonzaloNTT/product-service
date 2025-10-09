package com.bootcamp.product_service.application.mapper.command;

import com.bootcamp.product_service.domain.enums.TipoTarjeta;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record TarjetaCreditoCommand(
        String clienteId,
        TipoTarjeta tipoTarjeta,
        String moneda,
        BigDecimal limiteCredito
) {}

//Crear tarjeta credito
//Cerrrar tarjet credito
//Obtener tarjeta credito por cliente
