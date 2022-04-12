package com.springapp.app.indoorturfbooking.Controller;

import com.springapp.app.indoorturfbooking.Entity.*;

import com.springapp.app.indoorturfbooking.Exception.*;
import com.springapp.app.indoorturfbooking.Request.RequestModel;
import com.springapp.app.indoorturfbooking.Response.EditBooking;
import com.springapp.app.indoorturfbooking.Response.GroundBookingDates;
import com.springapp.app.indoorturfbooking.Response.ResponseModel;
import com.springapp.app.indoorturfbooking.Service.UserService;


import com.springapp.app.indoorturfbooking.Service.impl.UserServiceImpl;
import com.springapp.app.indoorturfbooking.Service.impl.userServ;
import com.springapp.app.indoorturfbooking.config.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.naming.AuthenticationException;
import java.util.List;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserService userService;

    @Autowired
    com.springapp.app.indoorturfbooking.Service.impl.userServ userServ;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;


    @RequestMapping(value="/authenticate",method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {
        final Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token =jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user) throws UserDataExistsException {
        return userService.save(user);
    }

        @PreAuthorize("hasRole('USER')")
        @RequestMapping(value = "/userpage", method = RequestMethod.POST)
        public String userping () {
            return "Any User can Access this page";
        }

        @PreAuthorize("hasRole('ADMIN')")
        @RequestMapping(value = "/adminpage", method = RequestMethod.POST)
        public String adminping () {
            return "Any admin can Access this page";
        }


    // Add Booking.
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/booking/add/{email}")
    public ResponseEntity<ResponseModel> addBooking(@RequestBody Bookings requestBooking, @PathVariable("email") String email)
            throws ResourceNotFoundException, UserNotAuthorizedException, BookingException, Exception
    {
        return new ResponseEntity<ResponseModel>(userServ.addBooking(requestBooking, email), HttpStatus.OK);
    }



    // Edit Booking.
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/editbookedgrounds/{id}")
    public  ResponseEntity<Bookings> editBooking(@PathVariable("id") long bookingId, @RequestBody EditBooking editBooking) throws ResourceNotFoundException, Exception
    {
        return new ResponseEntity<Bookings>(userServ.editBooking(bookingId, editBooking), HttpStatus.OK);
    }


    // Cancle Booking.
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/deletebookedgrounds/{id}")
    public ResponseEntity<ResponseModel> cancleBooking(@PathVariable("id") long bookingId) throws Exception
    {
        return new ResponseEntity<ResponseModel>(userServ.cancleBooking(bookingId), HttpStatus.OK);
    }


    // Display ground booking dates.
    @PreAuthorize("hasRole('USER')")
    @GetMapping("get/bookings/ground/{id}")
    public ResponseEntity<List<GroundBookingDates>> getBookingDatesOfGround(@PathVariable("id") long groundId) throws GroundException, Exception
    {
        return new ResponseEntity<List<GroundBookingDates>>(userServ.getBookingDatesOfGround(groundId), HttpStatus.OK);
    }


    // Display Customer Bookings.
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/bookedgrounds")
    public ResponseEntity<List<Bookings>> getCustomerBookings(@RequestBody RequestModel requestModel) throws ResourceNotFoundException, Exception
    {
        return new ResponseEntity<List<Bookings>>(userServ.getUserBookings(requestModel), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/bookedgrounds/history")
    // Display Customer Bookings History.
    public ResponseEntity<List<Bookings>> getCustomerBookingsHistory(@RequestBody RequestModel requestModel) throws ResourceNotFoundException, Exception
    {
        return new ResponseEntity<List<Bookings>>(userServ.getUserBookingsHistory(requestModel), HttpStatus.OK);
    }

    //Display all grounds.
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/dashboard")
    public ResponseEntity<List<Ground>> showAllGrounds()
    {
        return new ResponseEntity<List<Ground>>(userServ.showAllGrounds(), HttpStatus.OK);
    }




}


        // Login User.




