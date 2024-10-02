package com.ezechukwu.tfl.dto.request;

import com.ezechukwu.tfl.dto.response.CardResponse;
import com.ezechukwu.tfl.dto.response.StationPointResponse;
import com.ezechukwu.tfl.dto.response.TransportResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripCheckOutRequest {
    private CardResponse card;
    private StationPointResponse stationPoint;
    private TransportResponse transport;
}
