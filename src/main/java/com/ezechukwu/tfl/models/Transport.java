package com.ezechukwu.tfl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "transport")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transport {
    @Id
    @GeneratedValue
    private Integer transportId;
    private String transportName;
    @Enumerated(value = EnumType.STRING)
    private TransportMode transportMode;
    @ManyToMany(mappedBy = "stationTransport")
    private Set<Station> transportStation;

}
