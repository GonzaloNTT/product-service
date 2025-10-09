package com.bootcamp.product_service.domain.exception;

public class ClienteAlreadyExistsException extends DomainException {
    public ClienteAlreadyExistsException(String message) { super(message); }
}
