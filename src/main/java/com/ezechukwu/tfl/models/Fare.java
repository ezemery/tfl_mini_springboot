package com.ezechukwu.tfl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fare")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Fare {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer fareId;

    private Integer checkinZone;

    private Integer checkoutZone;

    private String transport_mode;
    private Double fare;

}
