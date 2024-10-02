package com.ezechukwu.tfl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "station_point")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StationPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer stationPointId;

    @OneToMany(mappedBy = "stationPoint",cascade = CascadeType.ALL)
    private List<Zone> zone;

    @ManyToOne
    @JoinColumn(name="station_id")
    private Station station;
}

