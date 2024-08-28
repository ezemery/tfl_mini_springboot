package com.ezechukwu.tfl.repositories;

import com.ezechukwu.tfl.models.Fare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FareRepository extends JpaRepository<Fare, Integer> {
    @Query("from Fare f where f.checkinZone=:checkInZone and f.checkoutZone=:checkOutZone")
    Fare findByCheckInAndCheckOutZone(@Param("checkInZone") Integer checkInZone, @Param("checkOutZone") Integer checkOutZone);
}
