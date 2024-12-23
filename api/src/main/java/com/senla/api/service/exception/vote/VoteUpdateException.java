package com.senla.api.service.exception.vote;

import com.senla.api.service.exception.ServiceException;

public class VoteUpdateException extends ServiceException {
    public VoteUpdateException(String message) {
        super(message);
    }
}
