package com.springapp.app.indoorturfbooking.Repository;

import java.util.List;

import com.springapp.app.indoorturfbooking.Entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
    public User findByEmail(String email);
    public User findByMobile(String mobile);
    public List<User> findByRole(String role);

}
