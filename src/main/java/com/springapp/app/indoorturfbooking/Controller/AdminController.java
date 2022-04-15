package com.springapp.app.indoorturfbooking.Controller;

import java.util.List;

import com.springapp.app.indoorturfbooking.Entity.Ground;
import com.springapp.app.indoorturfbooking.Entity.User;
import com.springapp.app.indoorturfbooking.Exception.GroundException;
import com.springapp.app.indoorturfbooking.Exception.ResourceNotFoundException;
import com.springapp.app.indoorturfbooking.Exception.UserDataExistsException;
import com.springapp.app.indoorturfbooking.Request.EmailLogin;
import com.springapp.app.indoorturfbooking.Response.BooleanResponseModel;
import com.springapp.app.indoorturfbooking.Response.Person;
import com.springapp.app.indoorturfbooking.Response.ResponseModel;
import com.springapp.app.indoorturfbooking.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    UserService userService;

     // SignUp new User. 
     @PostMapping("/signup")
     public ResponseEntity<ResponseModel> addNewUser(@RequestBody User user) throws UserDataExistsException, Exception
     {
         return new ResponseEntity<ResponseModel>(userService.addNewUser(user,"ADMIN"),HttpStatus.CREATED);
     }
 
 
     // Login User.
     @PostMapping("/login")
     public ResponseEntity<BooleanResponseModel> loginUserByEmail(@RequestBody EmailLogin login) throws ResourceNotFoundException, Exception
     {
         return new ResponseEntity<BooleanResponseModel>(userService.loginUserByEmail(login),HttpStatus.OK);
     }
     

     // Display all Customers.
     @GetMapping("/list/users")
     public ResponseEntity<List<Person>> getAllCustomers() throws ResourceNotFoundException, Exception
     {
        return new ResponseEntity<List<Person>>(userService.getAllUsersInfo("USER") ,HttpStatus.OK);
     }


     // Display all Admins.
     @GetMapping("/list/admins")
     public ResponseEntity<List<Person>> getAllAdmins() throws ResourceNotFoundException, Exception
     {
        return new ResponseEntity<List<Person>>(userService.getAllUsersInfo("ADMIN") ,HttpStatus.OK);
     }


     // Add New Ground
     @PostMapping("/addGround")
     public ResponseEntity<ResponseModel> addNewGround(@RequestBody Ground ground) throws GroundException, Exception
     {
        return new ResponseEntity<ResponseModel>(userService.addGround(ground), HttpStatus.ACCEPTED);
     }

     
     // Edit Ground
     @PutMapping("/editGround/{id}")
     public ResponseEntity<Ground> editGround(@PathVariable("id") long groundId, @RequestBody Ground ground) throws GroundException, Exception
     {
        return new ResponseEntity<Ground>(userService.editGround(groundId, ground), HttpStatus.OK);
     }


     @DeleteMapping("/deleteGround/{id}")
     public ResponseEntity<ResponseModel> deleteGround(@PathVariable("id") long groundId) throws GroundException, Exception
     {
        return new ResponseEntity<ResponseModel>(userService.deleteGround(groundId), HttpStatus.OK);
     }

     //Display all grounds.
     @GetMapping("/dashboard")
     public ResponseEntity<List<Ground>> showAllGrounds()
     {
         return new ResponseEntity<List<Ground>>(userService.showAllGrounds(), HttpStatus.OK);
     }
     
    
}
