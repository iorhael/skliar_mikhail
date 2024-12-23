package com.senla.api.dao.exception;

public class NoPublicationStatusesFoundException extends EntityNotFoundException {
    public NoPublicationStatusesFoundException(String message) {
        super(message);
    }
}
