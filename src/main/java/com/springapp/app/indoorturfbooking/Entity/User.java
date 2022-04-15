package com.springapp.app.indoorturfbooking.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
