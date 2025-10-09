package com.bootcamp.product_service.application.port.in;

import com.bootcamp.operation.GastoEvent;
import reactor.core.publisher.Mono;

public interface CustomerEventListenerPort {
    Mono<Void> ejecutarGasto(GastoEvent event);

}
