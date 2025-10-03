package com.bootcamp.customer_service.domain.valueobject;

import com.bootcamp.customer_service.domain.enums.DocumentType;

import java.util.Objects;

public record Document(DocumentType type, String number) {
    public Document {
        Objects.requireNonNull(type);
        Objects.requireNonNull(number);
    }
}
