package com.springapp.app.indoorturfbooking.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GroundException extends Exception{
    
    private String error;

    public GroundException(String error)
    {
         super(error);
         this.error = error;
    }

    public GroundException(){}

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
