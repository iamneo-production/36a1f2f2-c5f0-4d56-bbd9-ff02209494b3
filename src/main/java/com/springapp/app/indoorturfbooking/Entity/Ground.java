package com.springapp.app.indoorturfbooking.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ground")
public class Ground {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long groundId;
    
    @Column(nullable=false, unique=true)
    private String groundName;
    
    @Column(nullable=false)
    private String imageURL;
    
    @Column(nullable=false)
    private String groundAddress;
    
    @Column(nullable=false)
    private String groundDescription;
    
    private int capacity;

    private boolean status;
    
    @Column(nullable = false)
    private double price;

}
