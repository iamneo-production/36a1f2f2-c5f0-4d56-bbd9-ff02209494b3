package com.example.BookingIndoor.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "sl_no")
    private long id;             //Seriel Number of User.

    @Column(nullable = false, unique = true)
    private String email;         //EmailId of User.

    @Column(name = "uname", nullable = false)
    private String userName;      //Username of User.

    @Column(nullable = false, unique = true)
    private String mobile;        //Mobile number of User.

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_bookings")
    private List<Bookings> bookings;

    public User(String email, String userName, String mobile, String password, String role ) {
        this.email = email;
        this.userName = userName;
        this.mobile = mobile;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Bookings> getBookings() {
        return bookings;
    }

    public void setBookings(List<Bookings> bookings) {
        this.bookings = bookings;
    }
}
