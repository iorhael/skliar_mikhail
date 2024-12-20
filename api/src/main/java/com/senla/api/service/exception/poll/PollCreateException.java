package com.senla.api.service.exception.poll;

import com.senla.api.service.exception.ServiceException;

public class PollCreateException extends ServiceException {
    public PollCreateException(String message) {
        super(message);
    }
}
