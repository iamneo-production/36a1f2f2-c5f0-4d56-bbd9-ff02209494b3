package com.example.BookingIndoor.Exception;

import java.util.Date;


public class ErrorDetails {
    
    private Date timeStamp;
    private String message;
    private String request;

    public ErrorDetails(Date timeStamp, String message, String request) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.request = request;
    }

    public ErrorDetails() {
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
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
