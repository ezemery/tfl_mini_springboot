package com.ezechukwu.tfl.models;

import jakarta.persistence.*;


@Entity
@Table(name = "charge")
public class Charge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer chargeId;

    private Double amount;

    private Boolean isCharged = false;

    @OneToOne(mappedBy = "charge", cascade = CascadeType.ALL)
    private Trip trip;

    @ManyToOne
    @JoinColumn(name="card")
    private Card card;

    public Charge() {
    }

    public Charge(Double amount, Trip trip, Card card) {
        this.amount = amount;
        this.trip = trip;
        this.card = card;
        this.isCharged = false;
    }

    public Integer getChargeId() {
        return chargeId;
    }

    public void setChargeId(Integer chargeId) {
        this.chargeId = chargeId;
    }


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Boolean getCharged() {
        return isCharged;
    }

    public void setCharged(Boolean isCharged) {
        this.isCharged = isCharged;
    }
}
