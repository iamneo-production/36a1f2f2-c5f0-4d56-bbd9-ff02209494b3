package com.springapp.app.indoorturfbooking.Repository;

import com.springapp.app.indoorturfbooking.Entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
     User findByUsername(String username);
     User findByMobile(String mobile);
     User findByEmail(String email);


}
