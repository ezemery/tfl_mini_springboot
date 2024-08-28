package com.ezechukwu.tfl.models;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "transport")
public class Transport {
    @Id
    @GeneratedValue
    private Integer transportId;
    private String transportName;
    @Enumerated(value = EnumType.STRING)
    private TransportMode transportMode;
    @ManyToMany(mappedBy = "stationTransport")
    private Set<Station> transportStation;

    public Transport() {
    }

    public Transport(Integer transportId, String transportName, TransportMode transportMode, Set<Station> transportStation) {
        this.transportId = transportId;
        this.transportName = transportName;
        this.transportMode = transportMode;
        this.transportStation = transportStation;
    }

    public Integer getTransportId() {
        return transportId;
    }

    public void setTransportId(Integer transportId) {
        this.transportId = transportId;
    }

    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }

    public TransportMode getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(TransportMode transportMode) {
        this.transportMode = transportMode;
    }

    public Set<Station> getTransportStation() {
        return transportStation;
    }

    public void setTransportStation(Set<Station> transportStation) {
        this.transportStation = transportStation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transport transport = (Transport) o;
        return Objects.equals(transportId, transport.transportId) && Objects.equals(transportName, transport.transportName) && Objects.equals(transportMode, transport.transportMode) && Objects.equals(transportStation, transport.transportStation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transportId, transportName, transportMode, transportStation);
    }
}
