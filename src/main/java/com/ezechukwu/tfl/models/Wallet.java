package com.ezechukwu.tfl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "wallet")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue
    private Integer walletId;
    private Double availableBalance;
    private Double bookBalance;
    @OneToOne
    @JoinColumn(name="card_id")
    private Card card;

}
