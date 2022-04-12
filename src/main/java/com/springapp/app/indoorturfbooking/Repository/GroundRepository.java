package com.springapp.app.indoorturfbooking.Repository;

import com.springapp.app.indoorturfbooking.Entity.Ground;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroundRepository extends JpaRepository<Ground,Long> {
    Ground findByGroundName(String groundName);
}
