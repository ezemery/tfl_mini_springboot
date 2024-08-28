package com.ezechukwu.tfl.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue
    private Integer tridId;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    @OneToOne
    @JoinColumn(name="charge")
    private Charge charge;
    @ManyToOne
    @JoinColumn(name="card")
    private Card card;
    @ManyToOne
    @JoinColumn(name="checkin_station")
    private StationPoint checkInStationPoint;
    @ManyToOne
    @JoinColumn(name="checkout_station")
    private StationPoint checkOutStationPoint;

    @ManyToOne
    @JoinColumn(name="station")
    private StationPoint station;

    public Trip() {
    }

    public Trip( LocalDateTime checkInTime, LocalDateTime checkOutTime, Charge charge, Card card, StationPoint checkInStationPoint, StationPoint checkOutStationPoint, StationPoint station) {
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.charge = charge;
        this.card = card;
        this.checkInStationPoint = checkInStationPoint;
        this.checkOutStationPoint = checkOutStationPoint;
        this.station = station;
    }

    public Integer getTridId() {
        return tridId;
    }

    public void setTridId(Integer tridId) {
        this.tridId = tridId;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public Charge getCharge() {
        return charge;
    }

    public void setCharge(Charge charge) {
        this.charge = charge;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public StationPoint getCheckInStationPoint() {
        return checkInStationPoint;
    }

    public void setCheckInStationPoint(StationPoint checkInStationPoint) {
        this.checkInStationPoint = checkInStationPoint;
    }

    public StationPoint getCheckOutStationPoint() {
        return checkOutStationPoint;
    }

    public void setCheckOutStationPoint(StationPoint checkOutStationPoint) {
        this.checkOutStationPoint = checkOutStationPoint;
    }

    public StationPoint getStation() {
        return station;
    }

    public void setStation(StationPoint station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tridId=" + tridId +
                ", checkInTime=" + checkInTime +
                ", checkOutTime=" + checkOutTime +
                ", charge=" + charge +
                ", card=" + card +
                ", checkInStationPoint=" + checkInStationPoint +
                ", checkOutStationPoint=" + checkOutStationPoint +
                ", station=" + station +
                '}';
    }
}
