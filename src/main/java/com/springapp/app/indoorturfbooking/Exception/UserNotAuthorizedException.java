package com.springapp.app.indoorturfbooking.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserNotAuthorizedException extends Exception {

    private String error;

    public UserNotAuthorizedException(String error)
    {
        super(error);
        this.error = error;
    }

    public UserNotAuthorizedException(){}

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
