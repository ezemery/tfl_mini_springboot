package com.ezechukwu.tfl.services;

import com.ezechukwu.tfl.exception.NotFoundException;
import com.ezechukwu.tfl.models.Card;
import com.ezechukwu.tfl.models.Wallet;
import com.ezechukwu.tfl.dto.response.CardAndWalletResponse;
import com.ezechukwu.tfl.dto.response.CardResponse;
import com.ezechukwu.tfl.repositories.CardRepository;
import com.ezechukwu.tfl.repositories.WalletRepository;
import com.ezechukwu.tfl.services.impl.CardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private WalletRepository walletRepository;

    @InjectMocks
    private CardServiceImpl cardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterCard() {
        CardResponse cardRecord = new CardResponse(null, "Card Name", "Type", "Token");

        Card card = Card.builder()
                .cardName("Card Name")
                .cardToken("Token")
                .cardType("Type")
                .build();
        Wallet wallet = new Wallet();
        card.setWallet(wallet);

        when(cardRepository.save(any(Card.class))).thenReturn(card);

        CardAndWalletResponse result = cardService.registerCard(cardRecord);

        assertNotNull(result);
        assertEquals("Card Name", result.getCardName());
        assertEquals("Type", result.getCardType());
        assertNotNull(result.getWallet());
    }

    @Test
    void testGetCardById_CardExists() {
        Card card = Card.builder()
                .cardName("Card Name")
                .cardToken("Token")
                .cardType("Type")
                .build();

        card.setCardId(1);
        Wallet wallet = new Wallet();
        wallet.setWalletId(1);
        card.setWallet(wallet);

        when(cardRepository.findById(1)).thenReturn(Optional.of(card));

        CardAndWalletResponse result = cardService.getCardById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Card Name", result.getCardName());
        assertEquals("Type", result.getCardType());
        assertNotNull(result.getWallet());
    }

    @Test
    void testGetCardById_CardDoesNotExist() {
        when(cardRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> cardService.getCardById(1));
    }
}
