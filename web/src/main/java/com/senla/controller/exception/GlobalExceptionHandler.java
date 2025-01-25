package com.senla.controller.exception;

import com.senla.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            ConstraintViolationException.class,
            ServiceException.class
    })
    public String handleBadRequestException(Exception e, Model model) {
        log.error(e.getMessage());

        model.addAttribute("message", e.getMessage());
        return "error/400";
    }
}
