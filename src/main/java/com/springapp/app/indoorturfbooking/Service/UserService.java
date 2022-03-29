package com.springapp.app.indoorturfbooking.Service;

import com.springapp.app.indoorturfbooking.Entity.User;
import com.springapp.app.indoorturfbooking.Entity.UserDto;

import java.util.List;

public interface UserService {
    User save(UserDto user);
    List<User> finaAll();
    User findOne(String username);
}
