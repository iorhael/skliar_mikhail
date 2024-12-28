package com.senla.service.exception.comment;

import com.senla.service.exception.ServiceException;

public class CommentCreateException extends ServiceException {
    public CommentCreateException(String message) {
        super(message);
    }
}
