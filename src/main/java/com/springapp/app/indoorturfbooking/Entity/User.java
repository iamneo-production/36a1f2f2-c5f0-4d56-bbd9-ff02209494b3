package com.springapp.app.indoorturfbooking.Entity;


import java.util.Set;

import javax.persistence.*;


@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;        //User Id of User.

    @Column(nullable = false, unique = true)
    private String email;         //EmailId of User.

    @Column(name = "uname", nullable = false)
    private String username;      //Username of User.

    @Column(nullable = false, unique = true)
    private String mobile;        //Mobile number of User.

    @Column(nullable = false)
    private String password;

    private boolean isActive;

    /*@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_bookings")
    private List<BookingGround> bookings;
    */


    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="USER_ROLES",
    joinColumns = {
            @JoinColumn(name="USER_ID")
    },
    inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID")
    })
    private Set<Role> roles;



    public long getUserId() {
        return id;
    }

    public void setUserId(long userId) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
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


    public Set<Role> getRoles() {
        return roles;
    }


    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}
