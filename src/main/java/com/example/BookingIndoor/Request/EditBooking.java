package com.example.BookingIndoor.Request;

public class EditBooking {

    private String description;
    private int numberOfPersons;

    public EditBooking(String description, int numberOfPersons) {
        this.description = description;
        this.numberOfPersons = numberOfPersons;
    }

    public EditBooking() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }
}
