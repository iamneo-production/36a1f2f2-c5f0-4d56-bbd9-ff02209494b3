package com.example.BookingIndoor.Exception;

public class UserErrorDetails {
    
    private String message;
    private String request;

    public UserErrorDetails(String message, String request) {
        this.message = message;
        this.request = request;
    }

    public UserErrorDetails() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
