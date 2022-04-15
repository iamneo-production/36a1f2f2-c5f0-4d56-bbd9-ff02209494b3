package com.springapp.app.indoorturfbooking.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.springapp.app.indoorturfbooking.Entity.Bookings;
import com.springapp.app.indoorturfbooking.Entity.Ground;
import com.springapp.app.indoorturfbooking.Entity.User;
import com.springapp.app.indoorturfbooking.Exception.BookingException;
import com.springapp.app.indoorturfbooking.Exception.GroundException;
import com.springapp.app.indoorturfbooking.Exception.ResourceNotFoundException;
import com.springapp.app.indoorturfbooking.Exception.UserDataExistsException;
import com.springapp.app.indoorturfbooking.Exception.UserNotAuthorizedException;
import com.springapp.app.indoorturfbooking.Repository.BookingsRepository;
import com.springapp.app.indoorturfbooking.Repository.GroundRepository;
import com.springapp.app.indoorturfbooking.Repository.UserRepository;
import com.springapp.app.indoorturfbooking.Request.EditBooking;
import com.springapp.app.indoorturfbooking.Request.EmailLogin;
import com.springapp.app.indoorturfbooking.Request.RequestModel;
import com.springapp.app.indoorturfbooking.Response.BooleanResponseModel;
import com.springapp.app.indoorturfbooking.Response.GroundBookingDates;
import com.springapp.app.indoorturfbooking.Response.Person;
import com.springapp.app.indoorturfbooking.Response.ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookingsRepository bookingsRepository;

    @Autowired
    GroundRepository groundRepository;


    /* User Functions */

    // SignUp new User.
    public ResponseModel addNewUser(User user, String type) throws UserDataExistsException, Exception
    {
        User existingWithEmail = userRepository.findByEmail(user.getEmail());
        User existingWithMobile = userRepository.findByMobile(user.getMobile());

        if(existingWithEmail != null && existingWithMobile != null)
            throw new UserDataExistsException("User with the provided email and mobile already exists! :(");

        if(existingWithEmail != null)
            throw new UserDataExistsException("User with the provided email already exists! :(");
              
        if(existingWithMobile != null)      
             throw new UserDataExistsException("User with the provided mobile already exists! :(");
              
        user.setRole(type);     

        userRepository.save(user);

        return new ResponseModel("User added successfully! :)");
    }




   // Login User.
    public BooleanResponseModel loginUserByEmail(EmailLogin login) throws ResourceNotFoundException, Exception
    {
        User user = userRepository.findByEmail(login.getEmail());
         
        if (user != null && user.getPassword().equals(login.getPassword())) 
            return new BooleanResponseModel(true);
        
        return new BooleanResponseModel(false);
    }
    

    /* Booking Functions */
    
    //  Add Booking
    public ResponseModel addBooking(Bookings requestBooking, String email) throws ResourceNotFoundException, UserNotAuthorizedException, BookingException, Exception
    {
        // Create the instance of current day object with Time : 00-Hours/00-Minutes/00Seconds
        LocalDate localDate = LocalDate.now();
        Date currentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println("Current Date : " + currentDate);
        

        // Check for user exists or not.
        User user = userRepository.findByEmail(email);
        if(user == null)
            throw new ResourceNotFoundException("User", email);
        
        
        // Check for user role.
        if(!user.getRole().equals("USER"))
            throw new UserNotAuthorizedException("Access Denied! User is not a Customer. :(");    
        

        // Check for ground exists or not.
        Ground ground = groundRepository.findById(requestBooking.getGroundId())
        .orElseThrow(() -> new ResourceNotFoundException("Ground", Long.toString(requestBooking.getGroundId())));


        // Check for ground is available or not.
        if(!ground.isStatus())
            throw new BookingException("Ground is not available now. :(");


        // Check [(fromDate - todayDate) >= 0]
        if(requestBooking.getFromDate().before(currentDate))
            throw new BookingException("From date cannot be less than today's date.");


        // Check [(toDate - fromDate) >= 0]      
        if(requestBooking.getToDate().before(requestBooking.getFromDate()))
            throw new BookingException("To date cannot be less than From date.");
             

        // Check wheather bookings exists between the range of fromDate and toDate.
        List<Bookings> bookings = bookingsRepository
        .findByGroundIdEqualsAndFromDateGreaterThanEqualAndFromDateLessThanEqualOrGroundIdEqualsAndFromDateLessThanAndToDateGreaterThanEqual(
        requestBooking.getGroundId(), 
        requestBooking.getFromDate(), 
        requestBooking.getToDate(), 
        requestBooking.getGroundId(), 
        requestBooking.getFromDate(), 
        requestBooking.getFromDate());
        
        if(!bookings.isEmpty())
         throw new BookingException("Bookings are there within your range! :(");


        // Check [numberOfPersons <= 0]
        if(requestBooking.getNumberOfPersons() <= 0)
              throw new BookingException("Number of persons cannot be less than 1.");

        // Check [numberOfPersons <= groundCapacity] 
        if(requestBooking.getNumberOfPersons() > ground.getCapacity())
              throw new BookingException("Number of persons cannot be greater than ground capacity.");
        
        
        // Calculate cost and store in price.
        long numberOfdays = ChronoUnit.DAYS.between(requestBooking.getFromDate().toInstant(), requestBooking.getToDate().toInstant());
        
        if(numberOfdays == 0)
            numberOfdays += 1;
        
        requestBooking.setPrice(ground.getPrice() * ((double) numberOfdays));
        
        
        // Edit User Booking list.
         bookings = user.getBookings();
         requestBooking.setEmail(user.getEmail());;   
         bookings.add(requestBooking);
         user.setBookings(bookings);

        // Save User data. 
        userRepository.save(user);

        return new ResponseModel("Ground Booked successfully! :)");
    }



    // Edit Booking.
    public Bookings editBooking(long bookingId, EditBooking editBooking) throws ResourceNotFoundException, Exception
    {   
        LocalDate localDate = LocalDate.now();
        Date currentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println("Current Date : " + currentDate);

        Bookings booking = bookingsRepository.findById(bookingId)
        .orElseThrow(() -> new ResourceNotFoundException("Booking", Long.toString(bookingId)));
        
        if(booking.getToDate().before(currentDate))
            throw new BookingException("Cannot edit past bookings. :(");

        if(editBooking.getNumberOfPersons()<=0)
            throw new BookingException("Number of persons cannot be less than 1");

        // Check for numberOfPersons should not be greater than ground capacity.
        if(editBooking.getNumberOfPersons() >  groundRepository.getById(booking.getGroundId()).getCapacity())
            throw new BookingException("Number of persons cannot be greater than ground capacity.");

        booking.setDescription(editBooking.getDescription());
        booking.setNumberOfPersons(editBooking.getNumberOfPersons());

        return bookingsRepository.save(booking);
    }



    // Delete booking.
    public ResponseModel cancleBooking(long bookingId) throws ResourceNotFoundException, Exception
    {   
        LocalDate localDate = LocalDate.now();
        Date currentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println("Current Date : " + currentDate);

        Bookings booking = bookingsRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking", Long.toString(bookingId)));
        
        if(booking.getToDate().before(currentDate))
            throw new BookingException("Cannot cancle past bookings. :(");

        bookingsRepository.deleteById(bookingId);
        return new ResponseModel("Booking with id="+bookingId+" cancled successfully! :)");
    }


    /* Ground Functions */

    // Add Ground.
    public ResponseModel addGround(Ground ground) throws GroundException, Exception
    {
        Ground existingGround = groundRepository.findByGroundName(ground.getGroundName());
        if(existingGround != null)
             throw new GroundException("Ground already exists. :(");

        if(ground.getCapacity() < 100)     
             throw new GroundException("Ground capacity cannot be less than 100. :(");
            
        if(ground.getPrice() < 0)
            throw new GroundException("Ground price cannot be less than 0. :(");
        
        ground.setStatus(true);
        
        groundRepository.save(ground);
            
        return new ResponseModel("Ground added successfully. :)");
    }


    // Edit Ground.
    public Ground editGround(long groundId, Ground ground) throws GroundException, Exception
    {
        Ground existingGround = groundRepository.findById(groundId).orElseThrow(() -> new GroundException("Ground doesn't exists. :("));

        existingGround.setGroundName(ground.getGroundName());
        existingGround.setGroundAddress(ground.getGroundAddress());
        existingGround.setImageURL(ground.getImageURL());
        existingGround.setCapacity(ground.getCapacity());
        existingGround.setGroundDescription(ground.getGroundDescription());
        existingGround.setPrice(ground.getPrice());
        existingGround.setStatus(ground.isStatus());

        return groundRepository.save(existingGround);
    }


    // Delete Ground.
    public ResponseModel deleteGround(long groundId) throws GroundException, Exception
    {
        groundRepository.findById(groundId).orElseThrow(() -> new GroundException("Ground doesn't exists. :("));
        
        LocalDate localDate = LocalDate.now();
        Date currentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println("Current Date : " + currentDate);

        List<Bookings> bookings = bookingsRepository
        .findByGroundIdEqualsAndToDateGreaterThanEqualOrGroundIdEqualsAndFromDateGreaterThanEqual
        (groundId, currentDate, groundId, currentDate);

        if(!bookings.isEmpty())
            throw new  GroundException("Ground already have bookings. Cannot delete ground. :(");


        groundRepository.deleteById(groundId);    

        return new ResponseModel("Ground deleted successfully! :)");
    }


    /* Display Functions */

    // Display ground booking dates.
    public List<GroundBookingDates> getBookingDatesOfGround(long groundId) throws GroundException, Exception
    {
        // Check if the ground exists or not.
        groundRepository.findById(groundId).orElseThrow(() -> new GroundException("Ground not exists. :("));

        // Get all bookings by ground id.
        List<Bookings> bookings = bookingsRepository.findByGroundIdOrderByFromDateAsc(groundId);

        List<GroundBookingDates> dates = new ArrayList<GroundBookingDates>();

        if(!bookings.isEmpty())
        {
           for (Bookings booking : bookings) {
               dates.add(new GroundBookingDates(booking.getFromDate(), booking.getToDate()));
           }
        }

        return dates;
    }





    // Display user information.
    public List<Person> getAllUsersInfo(String role) throws ResourceNotFoundException, Exception
    {
        List<User> users = userRepository.findByRole(role);
        List<Person> persons = new ArrayList<Person>();

        for (User user : users) {
            persons.add(new Person(user.getUserName(), user.getEmail(), user.getMobile()));
        }

        return persons;
    }


    //  Display user active bookings.
    public List<Bookings> getUserBookings(RequestModel requestModel) throws ResourceNotFoundException, Exception
    {

        LocalDate localDate = LocalDate.now();
        Date currentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println("Current Date : " + currentDate);
        

        User user = userRepository.findByEmail(requestModel.getRequest());

        if(user == null)
         throw new ResourceNotFoundException("User", requestModel.getRequest());

        List<Bookings> bookings = bookingsRepository
        .findByEmailEqualsAndToDateGreaterThanEqualOrEmailEqualsAndFromDateGreaterThanEqual
        (requestModel.getRequest(), currentDate, 
        requestModel.getRequest(), currentDate);
        
        return bookings;
    }


     //  Display user bookings history.
     public List<Bookings> getUserBookingsHistory(RequestModel requestModel) throws ResourceNotFoundException, Exception
     {
 
         LocalDate localDate = LocalDate.now();
         Date currentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
         System.out.println("Current Date : " + currentDate);
         
 
         User user = userRepository.findByEmail(requestModel.getRequest());
 
         if(user == null)
          throw new ResourceNotFoundException("User", requestModel.getRequest());
 
         List<Bookings> bookings = bookingsRepository
        .findByEmailAndToDateLessThan(requestModel.getRequest(), currentDate);
         
        return bookings;
     }


     // Display all the grounds available.
     public List<Ground> showAllGrounds()
     {
         return groundRepository.findAll();
     }

}
