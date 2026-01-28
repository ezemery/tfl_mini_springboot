package com.ezechukwu.tfl.controllers;

import com.ezechukwu.tfl.dto.response.CardAndWalletResponse;
import com.ezechukwu.tfl.dto.response.CardResponse;
import com.ezechukwu.tfl.services.CardService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CardController.class)
class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    @Test
    void testRegisterCard() throws Exception {
        CardResponse cardRecord = new CardResponse(null, "Card Name", "Type", "Token");
        CardAndWalletResponse response = new CardAndWalletResponse(1, "Card Name", "Type", "Token", null);

        when(cardService.registerCard(any(CardResponse.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/card/create")
                        .contentType("application/json")
                        .content("{\"cardName\": \"Card Name\", \"cardType\": \"Type\", \"cardToken\": \"Token\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardName").value("Card Name"));
    }

    @Test
    void testGetCardById() throws Exception {
        CardAndWalletResponse response = new CardAndWalletResponse(1, "Card Name", "Type", "Token", null);

        when(cardService.getCardById(1)).thenReturn(response);

        mockMvc.perform(get("/api/v1/card/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardName").value("Card Name"));
    }
}
