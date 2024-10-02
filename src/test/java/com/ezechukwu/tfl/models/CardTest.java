package com.ezechukwu.tfl.models;

import com.ezechukwu.tfl.models.Card;
import com.ezechukwu.tfl.models.Wallet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void testCardProperties() {
        Card card = Card.builder()
                .cardName("Card Name")
                .cardToken("Token")
                .cardType("Type")
                .build();

        assertEquals("Card Name", card.getCardName());
        assertEquals("Type", card.getCardType());
        assertEquals("Token", card.getCardToken());

        Wallet wallet = new Wallet();
        card.setWallet(wallet);

        assertEquals(wallet, card.getWallet());
    }

    @Test
    void testCardEqualsAndHashcode() {
        Card card1 = Card.builder()
                .cardName("Card Name")
                .cardToken("Token")
                .cardType("Type")
                .build();
        ;
        card1.setCardId(1);

        Card card2 = Card.builder()
                .cardName("Card Name")
                .cardToken("Token")
                .cardType("Type")
                .build();

        card2.setCardId(1);

        assertEquals(card1, card2);
        assertEquals(card1.hashCode(), card2.hashCode());
    }
}
