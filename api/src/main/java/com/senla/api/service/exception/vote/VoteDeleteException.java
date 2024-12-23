package com.senla.api.service.exception.vote;

import com.senla.api.service.exception.ServiceException;

public class VoteDeleteException extends ServiceException {
    public VoteDeleteException(String message) {
        super(message);
    }
}
