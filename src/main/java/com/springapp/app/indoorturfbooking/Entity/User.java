package com.springapp.app.indoorturfbooking.Entity;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sl_no")
    private long sno;             //Seriel Number of User.

    @Column(name = "uid", nullable = false, unique = true)
    private String userId;        //User Id of User.

    @Column(nullable = false, unique = true)
    private String email;         //EmailId of User.

    @Column(name = "uname", nullable = false)
    private String userName;      //Username of User.

    @Column(nullable = false, unique = true)
    private String mobile;        //Mobile number of User.

    @Column(nullable = false)
    private String password;

    private boolean isActive;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_bookings")
    private List<Bookings> bookings;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="USER_ROLES",
    joinColumns = {
            @JoinColumn(name="USER_ID")
    },
    inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID")
    })
    private Set<Role> roles;

    public long getSno() {
        return sno;
    }

    public void setSno(long sno) {
        this.sno = sno;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Bookings> getBookings() {
        return bookings;
    }

    public void setBookings(List<Bookings> bookings) {
        this.bookings = bookings;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
