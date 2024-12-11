package com.senla.api.dao.exception;

public class NoVotesFoundException extends RuntimeException {
    public NoVotesFoundException(String message) {
        super(message);
    }
}
