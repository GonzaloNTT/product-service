package com.bootcamp.customer_service.domain.valueobject;

import java.util.Objects;

public record CustomerId(String value) {
    public CustomerId {
        Objects.requireNonNull(value);
    }
}
