package com.bootcamp.customer_service.domain.valueobject;

public record Email(String value) {
    public Email {
        if (!value.contains("@")) throw new IllegalArgumentException("Invalid email");
    }
}
