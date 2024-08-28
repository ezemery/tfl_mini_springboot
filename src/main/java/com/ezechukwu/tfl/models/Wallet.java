package com.ezechukwu.tfl.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue
    private Integer walletId;
    private Double availableBalance;
    private Double bookBalance;
    @OneToOne
    @JoinColumn(name="card_id")
    private Card card;

    public Wallet() {
        this.availableBalance = 0.0;
        this.bookBalance = 0.0;
    }

    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    public Double getAvailableBalance() {
        return availableBalance;
    }

    public void setAmount(Double amount) {
        this.availableBalance = amount;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setAvailableBalance(Double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public Double getBookBalance() {
        return bookBalance;
    }

    public void setBookBalance(Double bookBalance) {
        this.bookBalance = bookBalance;
    }
}
