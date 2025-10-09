package com.bootcamp.product_service.application.mapper.command;

import com.bootcamp.product_service.domain.enums.TipoTarjeta;

import java.time.LocalDateTime;

public record TarjetaDebitoCommand(
        String clienteId,
        String moneda,
        String cuentaPrincipalId
) {}

//Crear tarjeta debito
//Eliminar tarjeta debito
// cambiar cuenta principal
//Obtener tarjeta debito por id
