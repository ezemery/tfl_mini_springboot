package com.ezechukwu.tfl.records;

import java.time.LocalDateTime;


public record TripCheckInRecord(CardRecord card, LocalDateTime check_in_time, LocalDateTime check_out_time, StationPointRecord check_in_station, StationPointRecord check_out_station, Double fare) {
}
