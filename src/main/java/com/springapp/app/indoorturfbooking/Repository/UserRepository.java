package com.springapp.app.indoorturfbooking.Repository;

import com.springapp.app.indoorturfbooking.Entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
    public User findByEmail(String email);
    public User findByMobile(String mobile);
    public User findByUserId(String userId);
}
