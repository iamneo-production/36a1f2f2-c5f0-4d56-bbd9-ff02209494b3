package com.example.BookingIndoor.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookingException extends Exception{
    
    private String error;

    public BookingException(String error)
    {
         super(error);
         this.error = error;
    }

    public BookingException(){}

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
}
