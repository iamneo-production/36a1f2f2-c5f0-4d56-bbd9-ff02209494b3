package com.springapp.app.indoorturfbooking.Exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    
    private Date timeStamp;
    private String message;
    private String request;
    
}
