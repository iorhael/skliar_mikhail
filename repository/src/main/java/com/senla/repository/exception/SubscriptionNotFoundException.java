package com.senla.repository.exception;

public class SubscriptionNotFoundException extends EntityNotFoundException {
    public SubscriptionNotFoundException(String message) {
        super(message);
    }
}
