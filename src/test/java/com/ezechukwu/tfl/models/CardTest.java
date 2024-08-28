package com.ezechukwu.tfl.models;

import com.ezechukwu.tfl.models.Card;
import com.ezechukwu.tfl.models.Wallet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void testCardProperties() {
        Card card = new Card("Card Name", "Type", "Token");

        assertEquals("Card Name", card.getCardName());
        assertEquals("Type", card.getCardType());
        assertEquals("Token", card.getCardToken());

        Wallet wallet = new Wallet();
        card.setWallet(wallet);

        assertEquals(wallet, card.getWallet());
    }

    @Test
    void testCardEqualsAndHashcode() {
        Card card1 = new Card("Card Name", "Type", "Token");
        card1.setCardId(1);

        Card card2 = new Card("Card Name", "Type", "Token");
        card2.setCardId(1);

        assertEquals(card1, card2);
        assertEquals(card1.hashCode(), card2.hashCode());
    }
}
