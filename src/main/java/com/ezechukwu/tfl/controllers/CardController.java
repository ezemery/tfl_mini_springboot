package com.ezechukwu.tfl.controllers;

import com.ezechukwu.tfl.dto.response.CardAndWalletResponse;
import com.ezechukwu.tfl.dto.response.CardResponse;
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
    public CardAndWalletResponse registerCard(@RequestBody @Valid CardResponse cardRegistrationRequest) {
        return cardService.registerCard(cardRegistrationRequest);
    }
    @GetMapping(value = "/{id}")
    public CardAndWalletResponse getCardCardById(@PathVariable Integer id) {
        return cardService.getCardById(id);
    }
}
