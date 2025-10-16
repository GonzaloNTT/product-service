package com.bootcamp.product_service.infrastructure.messaging;

import com.bootcamp.operation.GastoEvent;
import com.bootcamp.product_service.application.port.in.CustomerEventListenerPort;
import com.bootcamp.customer_service.events.CustomerCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomerEventConsumer {
    private final CustomerEventListenerPort customerEventListenerPort;

    public CustomerEventConsumer(CustomerEventListenerPort customerEventListenerPort) {
        this.customerEventListenerPort = customerEventListenerPort;
    }

    @KafkaListener(topics = "operation-gasto-product", groupId = "product")
    public void listenEjecutarGasto(GastoEvent event) {
        log.info("📥 Received GastoEvent: {}", event);

        customerEventListenerPort.ejecutarGasto(event)
                .doOnSuccess(v -> log.info("✅ Gasto procesado correctamente: {}", event.getIdOperacion()))
                .doOnError(e -> log.error("❌ Error procesando GastoEvent: {}", event, e))
                .subscribe();
    }


}

