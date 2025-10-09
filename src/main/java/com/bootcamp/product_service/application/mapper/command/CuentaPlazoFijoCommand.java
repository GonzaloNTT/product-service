package com.bootcamp.product_service.application.mapper.command;

import com.bootcamp.product_service.domain.enums.TipoCuentaCorriente;
import com.bootcamp.product_service.domain.enums.TipoProductoBancario;
import com.bootcamp.product_service.domain.valueobject.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;


public record CuentaPlazoFijoCommand(
        String clienteId,
        String moneda
) {}

//Abrir plazo fijo
//cerrar plazo fijo
//obtener plazo fijo por id CLIENTE