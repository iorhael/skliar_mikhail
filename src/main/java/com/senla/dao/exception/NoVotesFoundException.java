package com.senla.dao.exception;

public class NoVotesFoundException extends RuntimeException {
    public NoVotesFoundException(String message) {
        super(message);
    }
}
