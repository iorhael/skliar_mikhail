package com.senla.api.dao.exception;

public class CommentNotFoundException extends EntityNotFoundException {
    public CommentNotFoundException(String message) {
        super(message);
    }
}
