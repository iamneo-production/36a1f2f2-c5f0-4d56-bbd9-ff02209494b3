package com.springapp.app.indoorturfbooking.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditBooking {

    private String description;
    private int numberOfPersons;
    
}
