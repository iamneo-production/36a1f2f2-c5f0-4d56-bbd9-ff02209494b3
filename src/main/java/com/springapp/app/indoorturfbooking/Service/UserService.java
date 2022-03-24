package com.springapp.app.indoorturfbooking.Service;

import com.springapp.app.indoorturfbooking.Entity.User;
import com.springapp.app.indoorturfbooking.Exception.ResourceNotFoundException;
import com.springapp.app.indoorturfbooking.Exception.UserDataExistsException;
import com.springapp.app.indoorturfbooking.Repository.UserRepository;
import com.springapp.app.indoorturfbooking.Request.EmailLogin;
import com.springapp.app.indoorturfbooking.Response.BooleanResponseModel;
import com.springapp.app.indoorturfbooking.Response.ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    
    // SignUp new User.
    public ResponseModel addNewUser(User user) throws UserDataExistsException, Exception
    {
        User existingWithEmail = userRepository.findByEmail(user.getEmail());
        User existingWithMobile = userRepository.findByMobile(user.getMobile());

        if(existingWithEmail != null && existingWithMobile != null)
            throw new UserDataExistsException("User with the provided email and mobile already exists! :(");

        if(existingWithEmail != null)
            throw new UserDataExistsException("User with the provided email already exists! :(");
              
        if(existingWithMobile != null)      
             throw new UserDataExistsException("User with the provided mobile already exists! :(");
              
    
        user.setUserId(user.getEmail()+"@user");     

        userRepository.save(user);

        return new ResponseModel("User added successfully! :)");
    }




   // Login User.
    public BooleanResponseModel loginUserByEmail(EmailLogin login) throws ResourceNotFoundException, Exception
    {
        User user = userRepository.findByEmail(login.getEmail());

        if(user == null)
            throw new ResourceNotFoundException("User", login.getEmail());

        if(user.getPassword().equals(login.getPassword()))
             user.setActive(true);
        else
             user.setActive(false);         

        userRepository.save(user);

        return new BooleanResponseModel(user.isActive());
    }
    

  
    


}
