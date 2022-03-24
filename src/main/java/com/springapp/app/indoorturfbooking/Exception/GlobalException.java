package com.springapp.app.indoorturfbooking.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {
    
    @ExceptionHandler(UserDataExistsException.class)
    public ResponseEntity<?> handleUserDataExistsException(UserDataExistsException exception, WebRequest request)
    {
        UserErrorDetails errorDetails = new UserErrorDetails(exception.getMessage(), request.getDescription(true));
        return new ResponseEntity<>(errorDetails ,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request)
    {
        UserErrorDetails errorDetails = new UserErrorDetails(exception.getMessage(), request.getDescription(true));
        return new ResponseEntity<>(errorDetails ,HttpStatus.NOT_FOUND);
    }

}
