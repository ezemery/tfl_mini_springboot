package com.ezechukwu.tfl.repositories;

import com.ezechukwu.tfl.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
    @Query("SELECT t FROM Trip t " +
            "JOIN t.card c " +
            "JOIN t.station s " +
            "JOIN s.station st " +
            "WHERE c.cardId = :id " +
            "AND t.checkOutStationPoint IS NULL " +
            "AND t.checkInStationPoint IS NOT NULL " +
            "ORDER BY t.checkInTime DESC LIMIT 1")
    Trip findByCardIdAndStationName(@Param("id") Integer id);
}
