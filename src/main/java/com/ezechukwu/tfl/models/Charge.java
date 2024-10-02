package com.ezechukwu.tfl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "charge")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

}
