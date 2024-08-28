package com.ezechukwu.tfl.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cardId;
    private String cardName;
    private String cardType;
    private String cardToken;

    @OneToOne(mappedBy = "card",cascade = CascadeType.ALL)
    private Wallet wallet;
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private Set<Charge> charge;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private Set<Trip> trip;
    public Card() {
    }

    public Card( String cardName, String cardType, String cardToken) {
        this.cardName = cardName;
        this.cardType = cardType;
        this.cardToken = cardToken;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardToken() {
        return cardToken;
    }

    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
