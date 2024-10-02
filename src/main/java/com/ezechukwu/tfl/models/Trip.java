package com.ezechukwu.tfl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "trip")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
}
