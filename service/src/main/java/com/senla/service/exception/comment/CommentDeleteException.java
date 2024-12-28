package com.senla.service.exception.comment;

import com.senla.service.exception.ServiceException;

public class CommentDeleteException extends ServiceException {
    public CommentDeleteException(String message) {
        super(message);
    }
}
