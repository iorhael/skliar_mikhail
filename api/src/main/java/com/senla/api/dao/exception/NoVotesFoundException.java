package com.senla.api.dao.exception;

public class NoVotesFoundException extends EntityNotFoundException {
    public NoVotesFoundException(String message) {
        super(message);
    }
}
