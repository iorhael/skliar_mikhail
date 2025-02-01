package com.senla.controller.exception;

import com.senla.controller.dto.ErrorMessageDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    public static final String MESSAGE = "message";
    private static final String VALIDATION_ERROR = "Validation Error";
    private static final String ENTITY_NOT_FOUND = "Entity not found";
    private static final String DATA_INTEGRITY_VIOLATION_ERROR = "Data integrity violation error";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageDto handleValidationViolationExceptions(MethodArgumentNotValidException exception,
                                                               HttpServletRequest request) {
        log.error(exception.getMessage());

        Map<String, String> details = new HashMap<>();
        exception.getFieldErrors()
                .forEach(error -> details.put(error.getField(), error.getDefaultMessage()));

        return ErrorMessageDto.builder()
                .error(VALIDATION_ERROR)
                .details(details)
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageDto handleEntityNotFoundException(EntityNotFoundException exception,
                                                         HttpServletRequest request) {
        log.error(exception.getMessage());

        return ErrorMessageDto.builder()
                .status(HttpStatus.NOT_FOUND)
                .error(ENTITY_NOT_FOUND)
                .details(Map.of(MESSAGE, exception.getMessage()))
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageDto handleSQLExceptions(Exception exception, HttpServletRequest request) {
        log.error(exception.getMessage());

        return ErrorMessageDto.builder()
                .error(DATA_INTEGRITY_VIOLATION_ERROR)
                .path(request.getRequestURI())
                .build();
    }
}
