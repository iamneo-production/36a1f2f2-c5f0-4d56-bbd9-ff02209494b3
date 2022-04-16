package com.example.BookingIndoor.Request;

public class RequestModel {
    
    private String request;

    public RequestModel(String request) {
        this.request = request;
    }

    public RequestModel() {
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
