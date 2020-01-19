package com.stakater.nordmart.customer.service.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String error) {
        super(error);
    }
}
