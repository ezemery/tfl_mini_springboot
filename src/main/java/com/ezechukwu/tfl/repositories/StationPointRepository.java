package com.ezechukwu.tfl.repositories;


import com.ezechukwu.tfl.models.StationPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationPointRepository extends JpaRepository<StationPoint, Integer> {
}
