package com.springapp.app.indoorturfbooking.Controller;

import java.util.List;

import com.springapp.app.indoorturfbooking.Entity.Admin;
import com.springapp.app.indoorturfbooking.Exception.ResourceNotFoundException;
import com.springapp.app.indoorturfbooking.Exception.UserDataExistsException;
import com.springapp.app.indoorturfbooking.Request.EmailLogin;
import com.springapp.app.indoorturfbooking.Response.BooleanResponseModel;
import com.springapp.app.indoorturfbooking.Response.Person;
import com.springapp.app.indoorturfbooking.Response.ResponseModel;
import com.springapp.app.indoorturfbooking.Service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;
    

    // SignUp new Admin. 
    @PostMapping("/signup")
    public ResponseEntity<ResponseModel> addNewAdmin(@RequestBody Admin admin) throws UserDataExistsException, Exception
    {
        return new ResponseEntity<ResponseModel>(adminService.addNewAdmin(admin),HttpStatus.CREATED);
    }
    

     // Login Admin.
     @PostMapping("/login")
     public ResponseEntity<BooleanResponseModel> loginUserByEmail(@RequestBody EmailLogin login) throws ResourceNotFoundException, Exception
     {
         return new ResponseEntity<BooleanResponseModel>(adminService.loginUserByEmail(login),HttpStatus.OK);
     }

     
     // Get all User details.
     @GetMapping("/get/users")
     public ResponseEntity<List<Person>> getAllUsers()
     {
         return new ResponseEntity<>(adminService.getAllUsers() ,HttpStatus.OK);
     }


     // Get all Admin details
     @GetMapping("/get/admins")
     public ResponseEntity<List<Person>> getAllAdmins()
     {
         return new ResponseEntity<>(adminService.getAllAdmins() ,HttpStatus.OK);
     }



}
