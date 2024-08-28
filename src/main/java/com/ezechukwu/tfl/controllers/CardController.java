package com.ezechukwu.tfl.controllers;

import com.ezechukwu.tfl.records.CardAndWalletRecord;
import com.ezechukwu.tfl.records.CardRecord;
import com.ezechukwu.tfl.services.CardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/card")
public class CardController {
    @Autowired
    private CardService cardService;
    @PostMapping(value = "/create")
    public CardAndWalletRecord registerCard(@RequestBody @Valid CardRecord cardRegistrationRequest) {
        return cardService.registerCard(cardRegistrationRequest);
    }
    @GetMapping(value = "/{id}")
    public CardAndWalletRecord getCardCardById(@PathVariable Integer id) {
        return cardService.getCardById(id);
    }
}
