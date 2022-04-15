package com.springapp.app.indoorturfbooking.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailLogin {
    
    private String email;
    private String password;

}
