package com.senla.repository.exception;

public class NoSubscriptionPlansFoundException extends EntityNotFoundException {
    public NoSubscriptionPlansFoundException(String message) {
        super(message);
    }
}
