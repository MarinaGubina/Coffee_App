package com.github.marinagubina.coffee.exception.handler;

import com.github.marinagubina.coffee.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionsHandler  extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ExceptionsHandler.class.getName());

    @ExceptionHandler(value = {CoffeeRecordNotFoundException.class, CoffeeMachineNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        String respBody = ex.getMessage();
        log.error("There is an exception: " + ex.getMessage());
        return handleExceptionInternal(ex, respBody, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {NotEnoughComponentsException.class, ContainersOverflowingExceptions.class,
            CoffeeMachineConditionException.class})
    protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
        String respBody = ex.getMessage();
        log.error("There is an exception: " + ex.getMessage());
        return handleExceptionInternal(ex, respBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}

