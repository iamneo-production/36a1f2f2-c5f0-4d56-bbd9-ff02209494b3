package com.example.BookingIndoor.Request;


public class EmailLogin {
    
    private String email;
    private String password;

    public EmailLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public EmailLogin() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
