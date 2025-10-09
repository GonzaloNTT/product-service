package com.bootcamp.product_service.application.mapper.command;

import com.bootcamp.product_service.domain.enums.TipoTarjeta;

import java.time.LocalDateTime;

public record YankiCommand(
        String clienteId,
        String tipoDocumento,
        String numeroDocumento,
        String telefono,
        String imei,
        String correo
) {}

//abrir yanki con datos no-cliente
//abrir yanki con datos cliente
//cerrar yanki
//obtener yanki por nocliente
//opbernet yanki por cliente
