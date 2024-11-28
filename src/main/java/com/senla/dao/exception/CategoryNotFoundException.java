package com.senla.dao.exception;

public class CategoryNotFoundException extends RuntimeException {
   public CategoryNotFoundException(String message) {
       super(message);
   }
}
