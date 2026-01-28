package com.ezechukwu.tfl.controllers;

import com.ezechukwu.tfl.dto.request.CardFundRequest;
import com.ezechukwu.tfl.services.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;
    @PostMapping(value = "/fund")
    public CardFundRequest fundCustomer(@RequestBody @Valid CardFundRequest customerFundRequest) {
        return walletService.fundCustomer(customerFundRequest);
    }
}
