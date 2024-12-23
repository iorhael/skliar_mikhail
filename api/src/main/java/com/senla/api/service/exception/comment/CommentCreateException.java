package com.senla.api.service.exception.comment;

import com.senla.api.service.exception.ServiceException;

public class CommentCreateException extends ServiceException {
    public CommentCreateException(String message) {
        super(message);
    }
}
