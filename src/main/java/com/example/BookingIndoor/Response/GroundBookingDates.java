package com.example.BookingIndoor.Response;

import java.util.Date;


public class GroundBookingDates {
    
    private Date fromDate;
    private Date toDate;

    public GroundBookingDates(Date fromDate, Date toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public GroundBookingDates() {
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
