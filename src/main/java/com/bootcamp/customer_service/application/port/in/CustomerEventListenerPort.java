package com.bootcamp.customer_service.application.port.in;

public interface CustomerEventListenerPort {
    void listen(String message);
}
