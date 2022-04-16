package com.example.BookingIndoor.Service;

import com.example.BookingIndoor.Exception.*;
import com.example.BookingIndoor.Model.Bookings;
import com.example.BookingIndoor.Model.Ground;
import com.example.BookingIndoor.Model.Review;
import com.example.BookingIndoor.Model.User;
import com.example.BookingIndoor.Repository.BookingsRepository;
import com.example.BookingIndoor.Repository.GroundRepository;
import com.example.BookingIndoor.Repository.ReviewRepository;
import com.example.BookingIndoor.Repository.UserRepository;
import com.example.BookingIndoor.Request.EditBooking;
import com.example.BookingIndoor.Request.RequestModel;
import com.example.BookingIndoor.Response.GroundBookingDates;
import com.example.BookingIndoor.Response.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class UserService {
    @Autowired
    UserRepository repo;

    @Autowired
    GroundService groundService;

    @Autowired
    GroundRepository groundRepository;

    @Autowired
    BookingsRepository bookingsRepository;

    @Autowired
    ReviewRepository reviewRepository;

    public User saveUser(User lm) {
        return repo.save(lm);
    }

    public void saveAdmin(User um) {
        repo.save(um);
    }

    public List<User> findAdmin() {
        return repo.findAll();
    }

    public User editAdmin(User al) {
        return repo.save(al);
    }

    @Transactional
    public void deleteAdmin(Long id) {
        repo.deleteById(id);
    }

    public void deleteUser(Long id) {
        repo.deleteById(id);
    }

     //  Add Booking
    public ResponseModel addBooking(Bookings requestBooking, String email,long groundId) throws ResourceNotFoundException, UserNotAuthorizedException, BookingException, Exception
    {
        // Create the instance of current day object with Time : 00-Hours/00-Minutes/00Seconds
        LocalDate localDate = LocalDate.now();
        Date currentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println("Current Date : " + currentDate);
        

        // Check for user exists or not.
        User user = repo.findByEmail(email);
        if(user == null)
            throw new ResourceNotFoundException("User", email);
        
        
        // Check for user role.
        if(!user.getRole().equals("USER"))
            throw new UserNotAuthorizedException("Access Denied! User is not a Customer. :(");    
        

        // Check for ground exists or not.
        Ground ground = groundRepository.findById(groundId)
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
        groundId,
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
        requestBooking.setGroundId(groundId);

        ground.setGroundId(groundId);
        ground.setStatus(false);
        groundRepository.save(ground);
        
        // Edit User Booking list.
         bookings = user.getBookings();
         requestBooking.setEmail(user.getEmail());;   
         bookings.add(requestBooking);
         user.setBookings(bookings);

        // Save User data. 
        repo.save(user);

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

    //  Display user active bookings.
    public List<Bookings> getUserBookings(String email) throws ResourceNotFoundException, Exception
    {

        LocalDate localDate = LocalDate.now();
        Date currentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println("Current Date : " + currentDate);


        User user = repo.findByEmail(email);

        if(user == null)
            throw new ResourceNotFoundException("User", email);

        List<Bookings> bookings = bookingsRepository
                .findByEmailEqualsAndToDateGreaterThanEqualOrEmailEqualsAndFromDateGreaterThanEqual
                        (email, currentDate,
                                email, currentDate);

        return bookings;
    }


    public List<User> findUser() {
        List<User> user=repo.findAll();
        List<User> users=new ArrayList<>();
        for(User i:user){
            if(i.getRole().equals("USER")){
                users.add(i);
            }
        }
        return users;

    }

    public void addReview(Review review) {
        if(review!=null) {
            reviewRepository.save(review);
        }
    }

    public List<Review> getAllReview(Long ground_id) throws GroundException,Exception {
        if(groundService.finByGroundId(ground_id)==null){
            throw new GroundException("Ground does not exists");
        }
        return reviewRepository.findByGroundId(ground_id);
    }

    public void deleteReview(int review_id) throws ReviewException {
        //check whether userId is isActive() then delete

        /*Review review=reviewRepository.findById(review_id).get();
        if((review.getUserId()).isActive()){}else{throw new ReviewException("You are supposed to delete this review")}*/

        if(reviewRepository.findById(review_id)==null){
            throw new ReviewException("Review Does not exists");
        }
        reviewRepository.deleteById(review_id);
    }

    public Review getReview(int id) throws ReviewException {
        if(reviewRepository.findById(id)==null){
            throw new ReviewException("Review Does not exists");
        }
        return reviewRepository.findById(id).get() ;
    }

    public Review findReviewById(Review review,int review_id, Long ground_id) throws ReviewException {
        Review re=reviewRepository.findById(review_id).get();
        if(re==null){
            throw new ReviewException("The Review Doesn't exists");
        }
        review.setGroundId(re.getGroundId());
        review.setId(re.getId());
        return reviewRepository.save(review);
    }

    public Bookings getBookingById(long bookingId) throws BookingException {
        Bookings booking=bookingsRepository.findById(bookingId).get();
        if(booking==null){
            throw new BookingException("Booking does not exist");
        }
        return booking;
    }

    public Bookings getBookingByGroundId(long groundId) throws GroundException, BookingException {
        Ground ground=groundRepository.findById(groundId).get();
        if(ground==null){
            throw new GroundException("Ground Does not exist");
        }
        Bookings booking=bookingsRepository.findByGroundId(groundId);
        return booking;
    }
}
