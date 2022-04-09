package com.springapp.app.indoorturfbooking.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
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
}
