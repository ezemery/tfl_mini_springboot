package com.ezechukwu.tfl.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "station")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer stationId;
    private String stationName;
    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    private List<StationPoint> stationPoint;

    @ManyToMany
    @JoinTable(
            name="station_transports",
            joinColumns = @JoinColumn(name="stationId"),
            inverseJoinColumns = @JoinColumn(name="transportId")
    )
    private Set<Transport> stationTransport;

    public Station() {
    }

    public Station(Integer stationId, String stationName, List<StationPoint> stationPoint, Set<Transport> stationTransport) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.stationPoint = stationPoint;
        this.stationTransport = stationTransport;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public List<StationPoint> getStationPoint() {
        return stationPoint;
    }

    public void setStationPoint(List<StationPoint> stationPoint) {
        this.stationPoint = stationPoint;
    }

    public Set<Transport> getStationTransport() {
        return stationTransport;
    }

    public void setStationTransport(Set<Transport> stationTransport) {
        this.stationTransport = stationTransport;
    }

    @Override
    public String toString() {
        return "Station{" +
                "stationId=" + stationId +
                ", stationName='" + stationName + '\'' +
                ", stationPoint=" + stationPoint +
                ", stationTransport=" + stationTransport +
                '}';
    }
}
