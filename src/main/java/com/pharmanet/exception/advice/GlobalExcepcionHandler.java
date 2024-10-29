package com.pharmanet.exception.advice;


import com.pharmanet.exception.AlreadyExistsException;
import com.pharmanet.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExcepcionHandler {
    private final Map<String, Object> errorMap = new HashMap<>();

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleInvalidArguments(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> resourceNotFound(ResourceNotFoundException exception){
        errorMap.put("Status", "Error");
        errorMap.put("Message", exception.getMessage());
        errorMap.put("Code", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> alreadyExistsException(AlreadyExistsException exception){
        errorMap.put("Status", "Error");
        errorMap.put("Message", exception.getMessage());
        errorMap.put("Code", HttpStatus.CONFLICT);

        return new ResponseEntity<>(errorMap, HttpStatus.CONFLICT);
    }
}
