package com.senla.dao.exception;

public class SubscriptionNotFoundException extends RuntimeException {
    public SubscriptionNotFoundException(String message) {
        super(message);
    }
}
