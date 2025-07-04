package com.davi.bank.shared.exceptions.handler;

import com.davi.bank.shared.exceptions.ExceptionResponse;
import com.davi.bank.user.domain.exceptions.DuplicatedUniqueFields;
import com.davi.bank.user.domain.exceptions.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomizedExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGenericResponse(Exception exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(),500, Instant.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(),404, Instant.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> handleIntegrityViolationException(DataIntegrityViolationException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(),400, Instant.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DuplicatedUniqueFields.class)
    public ResponseEntity<ExceptionResponse> handleDuplicatedUniqueFields(DuplicatedUniqueFields exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(),400, Instant.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
