package com.example.BookingIndoor.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="bookinggrounddetails")
public class Bookings {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;

    @Column(nullable = false)
    private long groundId;
    
    @Column(nullable = false)
    private Date fromDate;
    
    @Column(nullable = false)
    private Date toDate;

    @Column(nullable = false)
    private int numberOfPersons;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String email;

    private String description;

    public Bookings(long bookingId, long groundId, Date fromDate, Date toDate, int numberOfPersons, double price, String email, String description) {
        this.bookingId = bookingId;
        this.groundId = groundId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.numberOfPersons = numberOfPersons;
        this.price = price;
        this.email = email;
        this.description = description;
    }

    public Bookings() {
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public long getGroundId() {
        return groundId;
    }

    public void setGroundId(long groundId) {
        this.groundId = groundId;
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

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
