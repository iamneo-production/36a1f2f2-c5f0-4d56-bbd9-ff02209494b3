package com.springapp.app.indoorturfbooking.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserErrorDetails {
    
    private String message;
    private String request;

}
