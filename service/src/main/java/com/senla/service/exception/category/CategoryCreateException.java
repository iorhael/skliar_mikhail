package com.senla.service.exception.category;


import com.senla.service.exception.ServiceException;

public class CategoryCreateException extends ServiceException {
    public CategoryCreateException(String message) {
        super(message);
    }
}
