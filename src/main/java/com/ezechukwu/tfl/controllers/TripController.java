package com.ezechukwu.tfl.controllers;

import com.ezechukwu.tfl.dto.response.TripCheckInResponse;
import com.ezechukwu.tfl.dto.request.TripCheckInRequest;
import com.ezechukwu.tfl.dto.response.TripCheckOutResponse;
import com.ezechukwu.tfl.dto.request.TripCheckOutRequest;
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
    public TripCheckInResponse fundCustomer(@RequestBody @Valid TripCheckInRequest tripCheckInRequest) {
        return tripService.checkin(tripCheckInRequest);
    }

    @PostMapping(value = "/checkout")
    public TripCheckOutResponse fundCustomer(@RequestBody @Valid TripCheckOutRequest tripCheckInRequest) {
        return tripService.checkout(tripCheckInRequest);
    }

}
