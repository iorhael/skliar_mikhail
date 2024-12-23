package com.senla.api.service.exception.vote;

import com.senla.api.service.exception.ServiceException;

public class VoteCreateException extends ServiceException {
    public VoteCreateException(String message) {
        super(message);
    }
}
