package com.senla.di.exception;

public class BeanCreationException extends RuntimeException {
    public BeanCreationException(String beanType, String message) {
        super(String.format("Unable to create bean with type %s. %s", beanType, message));
    }
}
