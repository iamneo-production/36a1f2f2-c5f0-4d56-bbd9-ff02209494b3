package com.springapp.app.indoorturfbooking.Service;

import com.springapp.app.indoorturfbooking.Entity.User;
import com.springapp.app.indoorturfbooking.Entity.UserDto;
import com.springapp.app.indoorturfbooking.Exception.UserDataExistsException;

import java.util.List;

public interface UserService {
    User save(UserDto user) throws UserDataExistsException;
    List<User> finaAll();
    User findOne(String username);
}
