package com.ezechukwu.tfl.records;

import java.time.LocalDate;

public record TripCheckInRequest(CardRecord card, StationPointRecord station_point, TransportRecord transport) {
}
