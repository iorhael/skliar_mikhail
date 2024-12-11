package com.senla.api.dao.exception;

public class SubscriptionPlanNotFoundException extends RuntimeException {
    public SubscriptionPlanNotFoundException(String message) {
        super(message);
    }
}
