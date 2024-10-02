package com.ezechukwu.tfl.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripCheckInResponse {
    private CardResponse card;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private StationPointResponse checkInStation;
    private StationPointResponse checkOutStation;
    private Double fare;
}
