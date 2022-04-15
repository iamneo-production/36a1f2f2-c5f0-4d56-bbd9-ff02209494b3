package com.springapp.app.indoorturfbooking.Response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroundBookingDates {
    
    private Date fromDate;
    private Date toDate;

}
