package com.ezechukwu.tfl.services;

import com.ezechukwu.tfl.exception.NotFoundException;
import com.ezechukwu.tfl.models.Card;
import com.ezechukwu.tfl.models.Wallet;
import com.ezechukwu.tfl.records.CardAndWalletRecord;
import com.ezechukwu.tfl.records.CardRecord;
import com.ezechukwu.tfl.repositories.CardRepository;
import com.ezechukwu.tfl.repositories.WalletRepository;
import com.ezechukwu.tfl.services.CardService;
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
    private CardService cardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterCard() {
        CardRecord cardRecord = new CardRecord(null, "Card Name", "Type", "Token");

        Card card = new Card("Card Name", "Type", "Token");
        Wallet wallet = new Wallet();
        card.setWallet(wallet);

        when(cardRepository.save(any(Card.class))).thenReturn(card);

        CardAndWalletRecord result = cardService.registerCard(cardRecord);

        assertNotNull(result);
        assertEquals("Card Name", result.card_name());
        assertEquals("Type", result.car_type());
        assertNotNull(result.wallet());
    }

    @Test
    void testGetCardById_CardExists() {
        Card card = new Card("Card Name", "Type", "Token");
        card.setCardId(1);
        Wallet wallet = new Wallet();
        wallet.setWalletId(1);
        card.setWallet(wallet);

        when(cardRepository.findById(1)).thenReturn(Optional.of(card));

        CardAndWalletRecord result = cardService.getCardById(1);

        assertNotNull(result);
        assertEquals(1, result.id());
        assertEquals("Card Name", result.card_name());
        assertEquals("Type", result.car_type());
        assertNotNull(result.wallet());
    }

    @Test
    void testGetCardById_CardDoesNotExist() {
        when(cardRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> cardService.getCardById(1));
    }
}
