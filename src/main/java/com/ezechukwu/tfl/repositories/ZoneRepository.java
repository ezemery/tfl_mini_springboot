package com.ezechukwu.tfl.repositories;

import com.ezechukwu.tfl.models.Trip;
import com.ezechukwu.tfl.models.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ZoneRepository extends JpaRepository<Zone, Integer> {
    @Query("SELECT z FROM Zone z JOIN z.stationPoint st WHERE st.stationPointId = :id")
    Zone findZoneByStationPointId(@Param("id") Integer id);
}
