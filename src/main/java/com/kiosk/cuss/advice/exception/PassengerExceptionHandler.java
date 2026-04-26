package com.kiosk.cuss.advice.exception;

import com.kiosk.cuss.exception.PassengerNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class PassengerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {PassengerNotFoundException.class})
    public ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND,request);
    }
}
