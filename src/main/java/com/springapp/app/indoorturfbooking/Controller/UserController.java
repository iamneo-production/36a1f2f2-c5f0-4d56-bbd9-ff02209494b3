package com.springapp.app.indoorturfbooking.Controller;

import com.springapp.app.indoorturfbooking.Entity.User;
import com.springapp.app.indoorturfbooking.Exception.ResourceNotFoundException;
import com.springapp.app.indoorturfbooking.Exception.UserDataExistsException;
import com.springapp.app.indoorturfbooking.Request.EmailLogin;
import com.springapp.app.indoorturfbooking.Response.BooleanResponseModel;
import com.springapp.app.indoorturfbooking.Response.ResponseModel;
import com.springapp.app.indoorturfbooking.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserService userService;


    // SignUp new User. 
    @PostMapping("/signup")
    public ResponseEntity<ResponseModel> addNewUser(@RequestBody User user) throws UserDataExistsException, Exception
    {
        return new ResponseEntity<ResponseModel>(userService.addNewUser(user),HttpStatus.CREATED);
    }


    // Login User.
    @PostMapping("/login")
    public ResponseEntity<BooleanResponseModel> loginUserByEmail(@RequestBody EmailLogin login) throws ResourceNotFoundException, Exception
    {
        return new ResponseEntity<BooleanResponseModel>(userService.loginUserByEmail(login),HttpStatus.OK);
    }
    
}
