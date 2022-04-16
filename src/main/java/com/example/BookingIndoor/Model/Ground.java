package com.example.BookingIndoor.Model;

import javax.persistence.*;

@Entity
@Table(name="grounddetails")
public class Ground {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long groundId;

    @Column(nullable = false, unique = true)
    private String groundName;

    @Column(nullable = false)
    private String imageURL;

    @Column(nullable = false)
    private String groundAddress;

    @Column(nullable = false)
    private String groundDescription;

    private int capacity;

    private boolean status;

    @Column(nullable = false)
    private double price;

    public Ground() {
    }

    public Ground(long groundId, String groundName, String imageURL, String groundAddress,
                  String groundDescription, int capacity, boolean status, double price) {
        this.groundId = groundId;
        this.groundName = groundName;
        this.imageURL = imageURL;
        this.groundAddress = groundAddress;
        this.groundDescription = groundDescription;
        this.capacity = capacity;
        this.status = status;
        this.price = price;
    }

    public long getGroundId() {
        return groundId;
    }

    public void setGroundId(long groundId) {
        this.groundId = groundId;
    }

    public String getGroundName() {
        return groundName;
    }

    public void setGroundName(String groundName) {
        this.groundName = groundName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getGroundAddress() {
        return groundAddress;
    }

    public void setGroundAddress(String groundAddress) {
        this.groundAddress = groundAddress;
    }

    public String getGroundDescription() {
        return groundDescription;
    }

    public void setGroundDescription(String groundDescription) {
        this.groundDescription = groundDescription;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



}