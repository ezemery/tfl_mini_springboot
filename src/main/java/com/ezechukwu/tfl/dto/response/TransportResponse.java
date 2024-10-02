package com.ezechukwu.tfl.dto.response;

import com.ezechukwu.tfl.models.TransportMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransportResponse{
    private String stationName;
    private TransportMode transportMode;
}
