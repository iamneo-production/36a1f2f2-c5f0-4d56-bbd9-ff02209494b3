package com.springapp.app.indoorturfbooking.Controller;

import java.util.List;

import com.springapp.app.indoorturfbooking.Entity.Bookings;
import com.springapp.app.indoorturfbooking.Entity.Ground;
import com.springapp.app.indoorturfbooking.Entity.User;
import com.springapp.app.indoorturfbooking.Exception.BookingException;
import com.springapp.app.indoorturfbooking.Exception.GroundException;
import com.springapp.app.indoorturfbooking.Exception.ResourceNotFoundException;
import com.springapp.app.indoorturfbooking.Exception.UserDataExistsException;
import com.springapp.app.indoorturfbooking.Exception.UserNotAuthorizedException;
import com.springapp.app.indoorturfbooking.Request.EditBooking;
import com.springapp.app.indoorturfbooking.Request.EmailLogin;
import com.springapp.app.indoorturfbooking.Request.RequestModel;
import com.springapp.app.indoorturfbooking.Response.BooleanResponseModel;
import com.springapp.app.indoorturfbooking.Response.GroundBookingDates;
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
@RequestMapping("/user")
public class CustomerController {
    
    @Autowired
    UserService userService;


    // SignUp new User. 
    @PostMapping("/signup")
    public ResponseEntity<ResponseModel> addNewUser(@RequestBody User user) throws UserDataExistsException, Exception
    {
        return new ResponseEntity<ResponseModel>(userService.addNewUser(user,"USER"), HttpStatus.CREATED);
    }


    // Login User.
    @PostMapping("/login")
    public ResponseEntity<BooleanResponseModel> loginUserByEmail(@RequestBody EmailLogin login) throws ResourceNotFoundException, Exception
    {
        return new ResponseEntity<BooleanResponseModel>(userService.loginUserByEmail(login), HttpStatus.OK);
    }
    

    // Add Booking.
    @PutMapping("/booking/add/{email}")
    public ResponseEntity<ResponseModel> addBooking(@RequestBody Bookings requestBooking, @PathVariable("email") String email) 
    throws ResourceNotFoundException, UserNotAuthorizedException, BookingException, Exception
    {
        return new ResponseEntity<ResponseModel>(userService.addBooking(requestBooking, email), HttpStatus.OK);
    }   
    

    // Edit Booking.
    @PutMapping("/editbookedgrounds/{id}")
    public  ResponseEntity<Bookings> editBooking(@PathVariable("id") long bookingId, @RequestBody EditBooking editBooking) throws ResourceNotFoundException, Exception
    {
        return new ResponseEntity<Bookings>(userService.editBooking(bookingId, editBooking), HttpStatus.OK);
    }

    
    // Cancle Booking.
    @DeleteMapping("/deletebookedgrounds/{id}")
    public ResponseEntity<ResponseModel> cancleBooking(@PathVariable("id") long bookingId) throws Exception
    {
        return new ResponseEntity<ResponseModel>(userService.cancleBooking(bookingId), HttpStatus.OK);
    }


    // Display ground booking dates.
    @GetMapping("get/bookings/ground/{id}")
    public ResponseEntity<List<GroundBookingDates>> getBookingDatesOfGround(@PathVariable("id") long groundId) throws GroundException, Exception
    {
        return new ResponseEntity<List<GroundBookingDates>>(userService.getBookingDatesOfGround(groundId), HttpStatus.OK);   
    }


    // Display Customer Bookings.
    @GetMapping("/bookedgrounds")
    public ResponseEntity<List<Bookings>> getCustomerBookings(@RequestBody RequestModel requestModel) throws ResourceNotFoundException, Exception
    {
        return new ResponseEntity<List<Bookings>>(userService.getUserBookings(requestModel), HttpStatus.OK);   
    }
    
    @GetMapping("/bookedgrounds/history")
    // Display Customer Bookings History.
    public ResponseEntity<List<Bookings>> getCustomerBookingsHistory(@RequestBody RequestModel requestModel) throws ResourceNotFoundException, Exception
    {
        return new ResponseEntity<List<Bookings>>(userService.getUserBookingsHistory(requestModel), HttpStatus.OK);   
    }

    //Display all grounds.
    @GetMapping("/dashboard")
    public ResponseEntity<List<Ground>> showAllGrounds()
    {
        return new ResponseEntity<List<Ground>>(userService.showAllGrounds(), HttpStatus.OK);
    }

    
}
