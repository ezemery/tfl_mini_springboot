package com.ezechukwu.tfl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "station")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer stationId;
    private String stationName;
    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    private List<StationPoint> stationPoint;

    @ManyToMany
    @JoinTable(
            name="station_transports",
            joinColumns = @JoinColumn(name="stationId"),
            inverseJoinColumns = @JoinColumn(name="transportId")
    )
    private Set<Transport> stationTransport;
}
