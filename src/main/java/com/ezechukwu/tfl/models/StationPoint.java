package com.ezechukwu.tfl.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "station_point")
public class StationPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer stationPointId;

    @OneToMany(mappedBy = "stationPoint",cascade = CascadeType.ALL)
    private List<Zone> zone;

    @ManyToOne
    @JoinColumn(name="station_id")
    private Station station;

    public StationPoint() {
    }

    public StationPoint(Integer stationPointId, List<Zone> zone, Station station) {
        this.stationPointId = stationPointId;
        this.zone = zone;
        this.station = station;
    }

    public Integer getStationPointId() {
        return stationPointId;
    }

    public void setStationPointId(Integer stationPointId) {
        this.stationPointId = stationPointId;
    }

    public List<Zone> getZone() {
        return zone;
    }

    public void setZone(List<Zone> zone) {
        this.zone = zone;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}

