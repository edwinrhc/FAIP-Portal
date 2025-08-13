package com.fonavi.faip.app.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {

    record ErrorPayload(Instant timestamp, int status, String error, String path, Map<String,String> validation) { }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorPayload> handleValidation(HttpServletRequest req, MethodArgumentNotValidException ex) {
        Map<String,String> v = ex.getBindingResult().getFieldErrors()
                .stream().collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage, (a,b)-> a, LinkedHashMap::new));
        var p = new ErrorPayload(Instant.now(), 400, "Validation Error", req.getRequestURI(),null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(p);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorPayload> handleIllegalArg(HttpServletRequest req, IllegalArgumentException ex) {
        var p = new ErrorPayload(Instant.now(), 404, ex.getMessage(), req.getRequestURI(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(p);
    }

}
