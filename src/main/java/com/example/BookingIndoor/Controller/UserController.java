package com.example.BookingIndoor.Controller;
import com.example.BookingIndoor.Exception.*;
import com.example.BookingIndoor.Model.Bookings;
import com.example.BookingIndoor.Model.Ground;
import com.example.BookingIndoor.Model.Review;
import com.example.BookingIndoor.Model.User;
import com.example.BookingIndoor.Repository.UserRepository;
import com.example.BookingIndoor.Request.EditBooking;
import com.example.BookingIndoor.Request.RequestModel;
import com.example.BookingIndoor.Response.GroundBookingDates;
import com.example.BookingIndoor.Response.ResponseModel;
import com.example.BookingIndoor.Service.UserService;
import com.example.BookingIndoor.Service.GroundService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class UserController {
    @Autowired
    private UserRepository repo;

    @Autowired
    private UserService service;

    @Autowired
    private GroundService serv;


    private String Default="USER";

    @PostMapping("/signup")
    private String loginByUser(@RequestBody User um){
        /*String pwd=um.getPassword();
        String encryptPwd=pwdEncoder.encode(pwd);
        um.setPassword(encryptPwd);*/
        um.setRole(Default);
        User u=service.saveUser(um);
        return "Registration Successful";
    }

    @GetMapping("/dashboard")
    private ResponseEntity<List<Ground>> availableGround() throws BookingException, GroundException {
        List<Ground> allGrounds= serv.availableGround();
        return new ResponseEntity<>(allGrounds, HttpStatus.OK);
    }

     // Add Booking.
    @PutMapping("/addBooking/{email}/{groundId}")
    public ResponseEntity<ResponseModel> addBooking(@RequestBody Bookings requestBooking, @PathVariable("email") String email,@PathVariable("groundId") long groundId)
    throws ResourceNotFoundException, UserNotAuthorizedException, BookingException, Exception
    {
        return new ResponseEntity<ResponseModel>(service.addBooking(requestBooking, email,groundId), HttpStatus.OK);
    }   
    

    // Edit Booking.
    @PutMapping("/editbookedgrounds/{id}")
    public  ResponseEntity<Bookings> editBooking(@PathVariable("id") long bookingId, @RequestBody EditBooking editBooking) throws ResourceNotFoundException, Exception
    {
        return new ResponseEntity<Bookings>(service.editBooking(bookingId, editBooking), HttpStatus.OK);
    }

    
    // Cancle Booking.
    @DeleteMapping("/deletebookedgrounds/{id}")
    public ResponseEntity<ResponseModel> cancleBooking(@PathVariable("id") long bookingId) throws Exception
    {
        return new ResponseEntity<ResponseModel>(service.cancleBooking(bookingId), HttpStatus.OK);
    }

    //Get Booking By Id.
    @GetMapping("/getBookingById/{bookingId}")
    public ResponseEntity<Bookings> getBooking(@PathVariable("bookingId") long bookingId)throws Exception{
        return new ResponseEntity<Bookings>(service.getBookingById(bookingId),HttpStatus.OK);
    }

    // Display ground booking dates.
    @GetMapping("get/bookings/ground/{id}")
    public ResponseEntity<List<GroundBookingDates>> getBookingDatesOfGround(@PathVariable("id") long groundId) throws GroundException, Exception
    {
        return new ResponseEntity<List<GroundBookingDates>>(service.getBookingDatesOfGround(groundId), HttpStatus.OK);
    }


    // Display Customer Bookings.
    @GetMapping("/bookedgrounds/{email}")
    public ResponseEntity<List<Bookings>> getCustomerBookings(@PathVariable("email") String email) throws ResourceNotFoundException, Exception
    {
        return new ResponseEntity<List<Bookings>>(service.getUserBookings(email), HttpStatus.OK);
    }

    @PostMapping("/addreview/{user_email}/{ground_id}")
    private String addReview(@RequestBody Review r, @PathVariable(value="user_email") String user_email, @PathVariable(value="ground_id") Long ground_id) throws Exception {
        r.setGroundId(ground_id);
        r.setUserEmail(user_email);

        if(r!=null){
            service.addReview(r);
        }
        else{
            throw new Exception("Error");
        }
        return "Review added Successfully";
    }

    @GetMapping("/viewreview/{ground_id}")
    private List<Review> allReview(@PathVariable(value="ground_id") Long ground_id) throws GroundException,Exception {
        return service.getAllReview(ground_id);
    }

    @PutMapping("/editreview/{review_id}/{ground_id}")
    private String editReview(@RequestBody Review r,@PathVariable(value="review_id") int review_id,@PathVariable(value="ground_id") Long ground_id) throws ReviewException {
        Review add=service.findReviewById(r,review_id,ground_id);
        return "Review Edited Successfully";
    }

    @DeleteMapping("/deletereview/{review_id}/{ground_id}")
    private String deleteReview(@PathVariable(value="review_id")int review_id,@PathVariable(value="ground_id")Long ground_id) throws ReviewException {
        service.deleteReview(review_id);
        return "Review deleted successfully";
    }

    @GetMapping("/getReviewById/{id}")
    private Review getReview(@PathVariable(value="id")int id) throws ReviewException {
        return service.getReview(id);
    }
}