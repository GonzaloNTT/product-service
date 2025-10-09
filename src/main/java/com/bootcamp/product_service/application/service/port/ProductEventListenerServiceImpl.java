package com.bootcamp.product_service.application.service.port;

import com.bootcamp.operation.GastoEvent;
import com.bootcamp.product_service.application.port.in.CustomerEventListenerPort;
import com.bootcamp.product_service.domain.service.tarjets.TarjetaCreditoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ProductEventListenerServiceImpl implements CustomerEventListenerPort {
    TarjetaCreditoService tarjetaCreditoService;
    public ProductEventListenerServiceImpl(TarjetaCreditoService tarjetaCreditoService) {
        this.tarjetaCreditoService = tarjetaCreditoService;
    }

    @Override
    public Mono<Void> ejecutarGasto(GastoEvent message) {
        return tarjetaCreditoService.ejecutarGasto(message);
    }
}
