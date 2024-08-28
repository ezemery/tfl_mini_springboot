package com.ezechukwu.tfl.models;

import jakarta.persistence.*;

@Entity
@Table(name = "fare")
public class Fare {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer fareId;

    private Integer checkinZone;

    private Integer checkoutZone;

    private String transport_mode;
    private Double fare;

    public Fare() {
    }

    public Fare(Integer fareId, Integer checkinZone, Integer checkoutZone, String transport_mode) {
        this.fareId = fareId;
        this.checkinZone = checkinZone;
        this.checkoutZone = checkoutZone;
        this.transport_mode = transport_mode;
    }

    public Integer getFareId() {
        return fareId;
    }

    public void setFareId(Integer fareId) {
        this.fareId = fareId;
    }

    public Integer getCheckinZone() {
        return checkinZone;
    }

    public void setCheckinZone(Integer checkinZone) {
        this.checkinZone = checkinZone;
    }

    public Integer getCheckoutZone() {
        return checkoutZone;
    }

    public void setCheckoutZone(Integer checkoutZone) {
        this.checkoutZone = checkoutZone;
    }

    public String getTransport() {
        return transport_mode;
    }

    public void setTransportId(String transport) {
        this.transport_mode = transport_mode;
    }

    public String getTransport_mode() {
        return transport_mode;
    }

    public void setTransport_mode(String transport_mode) {
        this.transport_mode = transport_mode;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }
}
