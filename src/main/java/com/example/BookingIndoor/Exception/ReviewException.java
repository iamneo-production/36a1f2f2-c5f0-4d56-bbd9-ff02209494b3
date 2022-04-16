package com.example.BookingIndoor.Exception;

public class ReviewException extends Exception {

    private String error;

    public ReviewException(String error)
    {
        super(error);
        this.error = error;
    }

    public ReviewException(){}

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
