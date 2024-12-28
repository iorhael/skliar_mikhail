package com.senla.repository.exception;

public class NoVotesFoundException extends EntityNotFoundException {
    public NoVotesFoundException(String message) {
        super(message);
    }
}
