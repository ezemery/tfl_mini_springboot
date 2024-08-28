package com.ezechukwu.tfl.controllers;

import com.ezechukwu.tfl.records.TripCheckInRecord;
import com.ezechukwu.tfl.records.TripCheckInRequest;
import com.ezechukwu.tfl.records.TripCheckOutRecord;
import com.ezechukwu.tfl.records.TripCheckOutRequest;
import com.ezechukwu.tfl.services.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/v1/trip")
public class TripController {
    @Autowired
    private TripService tripService;
    @PostMapping(value = "/checkin")
    public TripCheckInRecord fundCustomer(@RequestBody @Valid TripCheckInRequest tripCheckInRequest) {
        return tripService.checkin(tripCheckInRequest);
    }

    @PostMapping(value = "/checkout")
    public TripCheckOutRecord fundCustomer(@RequestBody @Valid TripCheckOutRequest tripCheckInRequest) {
        return tripService.checkout(tripCheckInRequest);
    }

}
