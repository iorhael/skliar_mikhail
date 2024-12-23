package com.senla.api.dao.exception;

public class SubscriptionNotFoundException extends EntityNotFoundException {
    public SubscriptionNotFoundException(String message) {
        super(message);
    }
}
