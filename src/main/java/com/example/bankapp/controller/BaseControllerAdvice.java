package com.example.bankapp.controller;

import com.example.bankapp.exception.BaseException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class BaseControllerAdvice {
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> onBaseExceptionHandled(BaseException baseException) {
        return ResponseEntity.badRequest().body(new ApiError(baseException.getMessage()));
    }
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> onBaseExceptionHandled(IllegalArgumentException illegalArgumentException) {
        return ResponseEntity.badRequest().body(new ApiError(illegalArgumentException.getMessage()));
    }
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> onBaseExceptionHandled(DataIntegrityViolationException illegalArgumentException) {
        return ResponseEntity.badRequest().body(new ApiError(illegalArgumentException.getMessage()));
    }
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> onBaseExceptionHandled(ConstraintViolationException constraintViolationException) {
        return ResponseEntity.badRequest().body(new ApiError("Unique değer girilmelidir"));
    }
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> onBaseExceptionHandled(NullPointerException nullPointerException) {
        return ResponseEntity.badRequest().body(new ApiError(nullPointerException.getMessage()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        return ResponseEntity.badRequest().body(new ApiError(methodArgumentNotValidException.getFieldError().getDefaultMessage()));
    }

    public static record ApiError(String errorMessage) {
    }

}
