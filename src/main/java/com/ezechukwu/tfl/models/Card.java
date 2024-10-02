package com.ezechukwu.tfl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "card")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
}
