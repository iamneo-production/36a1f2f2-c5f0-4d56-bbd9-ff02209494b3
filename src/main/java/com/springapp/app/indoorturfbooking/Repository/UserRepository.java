package com.springapp.app.indoorturfbooking.Repository;

import com.springapp.app.indoorturfbooking.Entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
     User findByUsername(String username);

}
