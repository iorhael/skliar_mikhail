package com.senla.api.dao.exception;

public class NoSubscriptionPlansFoundException extends EntityNotFoundException {
    public NoSubscriptionPlansFoundException(String message) {
        super(message);
    }
}
