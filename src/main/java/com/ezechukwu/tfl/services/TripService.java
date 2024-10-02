package com.ezechukwu.tfl.services;

import com.ezechukwu.tfl.dto.request.TripCheckInRequest;
import com.ezechukwu.tfl.dto.request.TripCheckOutRequest;
import com.ezechukwu.tfl.dto.response.TripCheckInResponse;
import com.ezechukwu.tfl.dto.response.TripCheckOutResponse;

public interface TripService {
    TripCheckInResponse checkin(TripCheckInRequest tripCheckInRequest);
    TripCheckOutResponse checkout(TripCheckOutRequest tripCheckOutRequest);
}
