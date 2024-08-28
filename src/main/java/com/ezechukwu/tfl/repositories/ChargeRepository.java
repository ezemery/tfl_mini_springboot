package com.ezechukwu.tfl.repositories;

import com.ezechukwu.tfl.models.Charge;
import com.ezechukwu.tfl.models.Fare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargeRepository extends JpaRepository<Charge, Integer> {
    @Query("from Charge c JOIN c.card ca where ca.id=:id and c.isCharged=false")
    List<Charge> findByCardIdAndIsCharged(@Param("id") Integer id);
}
