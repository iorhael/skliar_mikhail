package com.senla.api.dao.exception;

public class SubscriptionNotFoundException extends RuntimeException {
    public SubscriptionNotFoundException(String message) {
        super(message);
    }
}
