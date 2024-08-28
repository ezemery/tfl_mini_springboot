package com.ezechukwu.tfl.controllers;

import com.ezechukwu.tfl.controllers.CardController;
import com.ezechukwu.tfl.records.CardAndWalletRecord;
import com.ezechukwu.tfl.records.CardRecord;
import com.ezechukwu.tfl.services.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CardController.class)
class CardControllerTest {

    @Mock
    private CardService cardService;

    @InjectMocks
    private CardController cardController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cardController).build();
    }

    @Test
    void testRegisterCard() throws Exception {
        CardRecord cardRecord = new CardRecord(null, "Card Name", "Type", "Token");
        CardAndWalletRecord response = new CardAndWalletRecord(1, "Card Name", "Type", "Token", null);

        when(cardService.registerCard(any(CardRecord.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/card/create")
                        .contentType("application/json")
                        .content("{\"card_name\": \"Card Name\", \"card_type\": \"Type\", \"card_token\": \"Token\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.card_name").value("Card Name"));
    }

    @Test
    void testGetCardById() throws Exception {
        CardAndWalletRecord response = new CardAndWalletRecord(1, "Card Name", "Type", "Token", null);

        when(cardService.getCardById(1)).thenReturn(response);

        mockMvc.perform(get("/api/v1/card/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.card_name").value("Card Name"));
    }
}
