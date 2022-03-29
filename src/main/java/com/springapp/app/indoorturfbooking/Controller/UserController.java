package com.springapp.app.indoorturfbooking.Controller;

import com.springapp.app.indoorturfbooking.Entity.AuthToken;
import com.springapp.app.indoorturfbooking.Entity.LoginUser;
import com.springapp.app.indoorturfbooking.Entity.User;
import com.springapp.app.indoorturfbooking.Entity.UserDto;

import com.springapp.app.indoorturfbooking.Service.UserService;


import com.springapp.app.indoorturfbooking.Service.impl.UserServiceImpl;
import com.springapp.app.indoorturfbooking.config.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.naming.AuthenticationException;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserService userService;



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
    public User saveUser(@RequestBody UserDto user) {
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



        }


        // Login User.




