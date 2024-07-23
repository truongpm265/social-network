package com.pmt.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDetails> userExceptionHandler(UserException e, WebRequest req){
        ErrorDetails error = new ErrorDetails(e.getMessage(),req.getDescription(false), LocalDateTime.now());

        return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception e, WebRequest req){
        ErrorDetails error = new ErrorDetails(e.getMessage(),req.getDescription(false), LocalDateTime.now());

        return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
    }
}
