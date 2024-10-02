package com.ezechukwu.tfl.controllers;

import com.ezechukwu.tfl.dto.response.CardAndWalletResponse;
import com.ezechukwu.tfl.dto.response.ChargeResponse;
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
    public CardAndWalletResponse chargeCard(@RequestBody @Valid ChargeResponse chargeCard) {
        return chargeService.chargeCard(chargeCard);
    }
}
