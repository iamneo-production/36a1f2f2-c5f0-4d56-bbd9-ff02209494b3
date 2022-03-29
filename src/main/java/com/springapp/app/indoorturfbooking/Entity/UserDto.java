package com.springapp.app.indoorturfbooking.Entity;

public class UserDto {
    private String username;
    private String password;
    private String email;
    private String mobile;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public User getUserFromDto() {
        User user=new User();
        user.setUserName(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setMobile(mobile);

        return user;
    }
}
