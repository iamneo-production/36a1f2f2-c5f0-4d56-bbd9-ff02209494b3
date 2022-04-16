package com.example.BookingIndoor.Exception;

import java.util.Date;

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
        UserErrorDetails errorDetails = new UserErrorDetails(exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails ,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookingException.class)
    public ResponseEntity<?> handleBookingException(BookingException exception, WebRequest request)
    {
        UserErrorDetails errorDetails = new UserErrorDetails(exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails ,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GroundException.class)
    public ResponseEntity<?> handleGroundException(GroundException exception, WebRequest request)
    {
        UserErrorDetails errorDetails = new UserErrorDetails(exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails ,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request)
    {
        UserErrorDetails errorDetails = new UserErrorDetails(exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails ,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotAuthorizedException.class)
    public ResponseEntity<?> handleUserNotAuthorizedException(UserNotAuthorizedException exception, WebRequest request)
    {
        UserErrorDetails errorDetails = new UserErrorDetails(exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails ,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request)
    {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
