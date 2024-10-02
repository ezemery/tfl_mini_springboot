package com.ezechukwu.tfl.utils;

import com.ezechukwu.tfl.dto.response.CardAndWalletResponse;
import com.ezechukwu.tfl.dto.response.WalletResponse;
import com.ezechukwu.tfl.models.Card;

import java.util.Optional;

public class Utils {
    public static CardAndWalletResponse getCardWalletResponse(Optional<Card> card ){
        WalletResponse walletResponse = WalletResponse.builder()
                .id(card.get().getWallet().getWalletId())
                .amount(card.get().getWallet().getAvailableBalance())
                .build();

        return CardAndWalletResponse.builder()
                .id(card.get().getCardId())
                .cardType(card.get().getCardType())
                .cardToken(card.get().getCardToken())
                .cardName(card.get().getCardName())
                .wallet(walletResponse)
                .build();
    }

    public static CardAndWalletResponse getCardWalletResponse(Card card ){
        WalletResponse walletResponse = WalletResponse.builder()
                .id(card.getWallet().getWalletId())
                .amount(card.getWallet().getAvailableBalance())
                .build();

        return CardAndWalletResponse.builder()
                .id(card.getCardId())
                .cardName(card.getCardName())
                .cardToken(card.getCardToken())
                .cardType(card.getCardType())
                .wallet(walletResponse)
                .build();
    }
}
