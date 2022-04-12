package com.springapp.app.indoorturfbooking.Service.impl;

import com.springapp.app.indoorturfbooking.Entity.Role;
import com.springapp.app.indoorturfbooking.Entity.User;
import com.springapp.app.indoorturfbooking.Entity.UserDto;
import com.springapp.app.indoorturfbooking.Exception.UserDataExistsException;
import com.springapp.app.indoorturfbooking.Repository.RoleRepository;
import com.springapp.app.indoorturfbooking.Repository.UserRepository;


import com.springapp.app.indoorturfbooking.Service.RoleService;
import com.springapp.app.indoorturfbooking.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value="userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Lazy
    @Autowired
    private BCryptPasswordEncoder bCryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);
        if(user==null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthority(user));
    }
    private Set<SimpleGrantedAuthority> getAuthority(User user){
        Set<SimpleGrantedAuthority> authorities=new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));

        });
        return authorities;
    }

    @Override
    public User save(UserDto user) throws UserDataExistsException {
        User existingWithEmail = userRepository.findByEmail(user.getEmail());
        User existingWithMobile = userRepository.findByMobile(user.getMobile());

        if(existingWithEmail != null && existingWithMobile != null)
            throw new UserDataExistsException("User with the provided email and mobile already exists! :(");

        if(existingWithEmail != null)
            throw new UserDataExistsException("User with the provided email already exists! :(");

        if(existingWithMobile != null)
            throw new UserDataExistsException("User with the provided mobile already exists! :(");
        User nUser=user.getUserFromDto();
        nUser.setPassword(bCryptEncoder.encode(user.getPassword()));




        Role role = roleService.findByName("USER");
        Set<Role> roleSet=new HashSet<>();
        roleSet.add(role);
        nUser.setRoles(roleSet);
        return userRepository.save(nUser);
    }

    public User savea(UserDto user) throws UserDataExistsException {
        User existingWithEmail = userRepository.findByEmail(user.getEmail());
        User existingWithMobile = userRepository.findByMobile(user.getMobile());

        if(existingWithEmail != null && existingWithMobile != null)
            throw new UserDataExistsException("User with the provided email and mobile already exists! :(");

        if(existingWithEmail != null)
            throw new UserDataExistsException("User with the provided email already exists! :(");

        if(existingWithMobile != null)
            throw new UserDataExistsException("User with the provided mobile already exists! :(");

        User nUser=user.getUserFromDto();
        nUser.setPassword(bCryptEncoder.encode(user.getPassword()));
           Role role=roleService.findByName("ADMIN");
        Set<Role> roleSet=new HashSet<>();
            roleSet.add(role);

        nUser.setRoles(roleSet);
        return userRepository.save(nUser);
    }

    @Override
    public List<User> finaAll() {
        List<User> list=new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findOne(String username) {
        return userRepository.findByUsername(username);
    }





}
