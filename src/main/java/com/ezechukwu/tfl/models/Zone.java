package com.ezechukwu.tfl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "zone")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Zone {
    @Id
    @GeneratedValue
    private Integer zoneId;
    private Integer zoneNumber;
    @ManyToOne
    @JoinColumn(name="station_point_id")
    private StationPoint stationPoint;
}
