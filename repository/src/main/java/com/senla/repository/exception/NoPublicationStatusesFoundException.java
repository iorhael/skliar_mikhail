package com.senla.repository.exception;

public class NoPublicationStatusesFoundException extends EntityNotFoundException {
    public NoPublicationStatusesFoundException(String message) {
        super(message);
    }
}
