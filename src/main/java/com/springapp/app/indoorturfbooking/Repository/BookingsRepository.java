package com.springapp.app.indoorturfbooking.Repository;

import com.springapp.app.indoorturfbooking.Entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BookingsRepository extends JpaRepository<Bookings,Long> {
    public List<Bookings>
    findByGroundIdEqualsAndFromDateGreaterThanEqualAndFromDateLessThanEqualOrGroundIdEqualsAndFromDateLessThanAndToDateGreaterThanEqual
            (long groundID1, Date fromDate, Date toDate, long groundId2, Date fromDate2, Date fromDate3);

    public List<Bookings> findByGroundIdOrderByFromDateAsc(long groundId);

    public List<Bookings> findByEmailEqualsAndToDateGreaterThanEqualOrEmailEqualsAndFromDateGreaterThanEqual
            (String email1, Date currentDate1, String email2, Date currentDate2);

    public List<Bookings> findByEmailAndToDateLessThan(String email, Date currentDate);

    public List<Bookings> findByGroundIdEqualsAndToDateGreaterThanEqualOrGroundIdEqualsAndFromDateGreaterThanEqual
            (long groundId1, Date currentDate1, long groundId2, Date currentDate2);

}
