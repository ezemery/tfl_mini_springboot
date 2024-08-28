package com.ezechukwu.tfl.models;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "zone")
public class Zone {
    @Id
    @GeneratedValue
    private Integer zoneId;
    private Integer zoneNumber;
    @ManyToOne
    @JoinColumn(name="station_point_id")
    private StationPoint stationPoint;

    public Zone() {
    }

    public Zone(Integer zoneId, Integer zoneNumber, StationPoint stationPoint) {
        this.zoneId = zoneId;
        this.zoneNumber = zoneNumber;
        this.stationPoint = stationPoint;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public Integer getZoneNumber() {
        return zoneNumber;
    }

    public void setZoneNumber(Integer zoneNumber) {
        this.zoneNumber = zoneNumber;
    }

    public StationPoint getZoneStations() {
        return stationPoint;
    }

    public void setZoneStations(StationPoint zoneStations) {
        this.stationPoint = stationPoint;
    }

}
