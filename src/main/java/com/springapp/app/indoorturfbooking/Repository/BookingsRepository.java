package com.springapp.app.indoorturfbooking.Repository;

import com.springapp.app.indoorturfbooking.Entity.Bookings;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingsRepository extends JpaRepository<Bookings, Long> {
    
}
