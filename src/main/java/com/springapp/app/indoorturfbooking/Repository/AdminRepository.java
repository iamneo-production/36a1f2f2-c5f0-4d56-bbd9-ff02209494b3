package com.springapp.app.indoorturfbooking.Repository;

import com.springapp.app.indoorturfbooking.Entity.Admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    public Admin findByEmail(String email);
    public Admin findByMobile(String mobile);
    public Admin findByAdminId(String adminId);
    
}
