package com.senla.service.exception.category;


import com.senla.service.exception.ServiceException;

public class CategoryUpdateException extends ServiceException {
    public CategoryUpdateException(String message) {
        super(message);
    }
}
