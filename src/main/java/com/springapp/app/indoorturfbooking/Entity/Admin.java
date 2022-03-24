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
@Table
public class Admin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sl_no")
    private long sno;             //Seriel Number of User.

    @Column(name = "aid", nullable = false, unique = true)
    private String adminId;        //User Id of User.

    @Column(nullable = false, unique = true)
    private String email;         //EmailId of User.

    @Column(name = "uname", nullable = false)
    private String userName;      //Username of User.

    @Column(nullable = false, unique = true)
    private String mobile;        //Mobile number of User.

    @Column(nullable = false)
    private String password;

    private boolean isActive;

}