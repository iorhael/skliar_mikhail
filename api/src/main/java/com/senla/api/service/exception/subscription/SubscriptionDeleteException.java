package com.senla.api.service.exception.subscription;

import com.senla.api.service.exception.ServiceException;

public class SubscriptionDeleteException extends ServiceException {
    public SubscriptionDeleteException(String message) {
        super(message);
    }
}
