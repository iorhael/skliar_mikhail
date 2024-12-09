package com.senla.api.dao.exception;

public class NoSubscriptionPlansFoundException extends RuntimeException {
    public NoSubscriptionPlansFoundException(String message) {
        super(message);
    }
}
