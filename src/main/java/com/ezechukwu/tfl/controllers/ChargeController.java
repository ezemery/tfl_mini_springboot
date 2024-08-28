package com.ezechukwu.tfl.controllers;

import com.ezechukwu.tfl.records.CardAndWalletRecord;
import com.ezechukwu.tfl.records.ChargeRecord;
import com.ezechukwu.tfl.services.ChargeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/charge")
public class ChargeController {
    @Autowired
    private ChargeService chargeService;
    @PostMapping(value = "/card")
    public CardAndWalletRecord chargeCard(@RequestBody @Valid ChargeRecord chargeCard) {
        return chargeService.chargeCard(chargeCard);
    }
}
