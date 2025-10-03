package com.bootcamp.customer_service.domain.valueobject;

import java.util.Objects;

public record Phone(String number, String imei) {
    public Phone {
        Objects.requireNonNull(number);
        Objects.requireNonNull(imei);
        if (!number.matches("\\+?[0-9]+")) throw new IllegalArgumentException("Invalid phone");
        if (!imei.matches("\\+?[0-9]+")) throw new IllegalArgumentException("Invalid imei");
    }
}