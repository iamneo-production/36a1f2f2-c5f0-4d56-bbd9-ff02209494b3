package com.springapp.app.indoorturfbooking.Service;

import java.util.ArrayList;
import java.util.List;

import com.springapp.app.indoorturfbooking.Entity.Admin;
import com.springapp.app.indoorturfbooking.Entity.Bookings;
import com.springapp.app.indoorturfbooking.Entity.User;
import com.springapp.app.indoorturfbooking.Exception.ResourceNotFoundException;
import com.springapp.app.indoorturfbooking.Exception.UserDataExistsException;
import com.springapp.app.indoorturfbooking.Repository.AdminRepository;
import com.springapp.app.indoorturfbooking.Repository.UserRepository;
import com.springapp.app.indoorturfbooking.Request.EmailLogin;
import com.springapp.app.indoorturfbooking.Response.BooleanResponseModel;
import com.springapp.app.indoorturfbooking.Response.Person;
import com.springapp.app.indoorturfbooking.Response.ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserRepository userRepository;



    // SignUp new Admin.
    public ResponseModel addNewAdmin(Admin admin) throws UserDataExistsException, Exception
    {
        Admin existingWithEmail = adminRepository.findByEmail(admin.getEmail());
        Admin existingWithMobile = adminRepository.findByMobile(admin.getMobile());

        if(existingWithEmail != null && existingWithMobile != null)
            throw new UserDataExistsException("Admin with the provided email and mobile already exists! :(");

        if(existingWithEmail != null)
            throw new UserDataExistsException("Admin with the provided email already exists! :(");
              
        if(existingWithMobile != null)      
             throw new UserDataExistsException("Admin with the provided mobile already exists! :(");
              
    
        admin.setAdminId(admin.getEmail()+"@admin");
        adminRepository.save(admin);

        return new ResponseModel("Admin added successfully! :)");
    }




     // Login Admin.
    public BooleanResponseModel loginUserByEmail(EmailLogin login) throws ResourceNotFoundException, Exception
    {
        Admin admin = adminRepository.findByEmail(login.getEmail());

        if(admin == null)
            throw new ResourceNotFoundException("Admin", login.getEmail());

        if(admin.getPassword().equals(login.getPassword()))
             admin.setActive(true);
        else
             admin.setActive(false);         

        adminRepository.save(admin);

        return new BooleanResponseModel(admin.isActive());
    }




     // Get all Users.
     public List<Person> getAllUsers()
     {
        List<Person> list = new ArrayList<Person>();
        List<User> users = userRepository.findAll();

        for (User user : users) {
            list.add(new Person(user.getUserName(), user.getEmail(), user.getMobile(), user.getBookings()));
        }

        return list;
     }


     

      // Get all Admins.
     public List<Person> getAllAdmins()
     {
        List<Person> list = new ArrayList<Person>();
        List<Admin> admins = adminRepository.findAll();

        for (Admin admin : admins) {
            list.add(new Person(admin.getUserName(), admin.getEmail(), admin.getMobile(), new ArrayList<Bookings>()));
        }

        return list;
     }






}
