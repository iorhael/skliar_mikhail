package com.senla.api.service.exception.category;

import com.senla.api.service.exception.ServiceException;

public class CategoryCreateException extends ServiceException {
    public CategoryCreateException(String message) {
        super(message);
    }
}
