package com.springapp.app.indoorturfbooking.Response;

import java.util.List;

import com.springapp.app.indoorturfbooking.Entity.Bookings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    
    private String name;
    private String email;
    private String mobile;
    private List<Bookings> bookings;
}
