package com.ezechukwu.tfl.services.impl;

import com.ezechukwu.tfl.exception.NotFoundException;
import com.ezechukwu.tfl.models.Card;
import com.ezechukwu.tfl.models.Wallet;
import com.ezechukwu.tfl.dto.response.CardAndWalletResponse;
import com.ezechukwu.tfl.dto.response.CardResponse;
import com.ezechukwu.tfl.dto.response.WalletResponse;
import com.ezechukwu.tfl.repositories.CardRepository;
import com.ezechukwu.tfl.repositories.WalletRepository;
import com.ezechukwu.tfl.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.ezechukwu.tfl.utils.Utils.getCardWalletResponse;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private WalletRepository walletRepository;

    public CardAndWalletResponse registerCard(CardResponse request) {
        Card newCard = Card.builder()
                .cardName(request.getCardName())
                .cardToken(request.getCardToken())
                .cardType(request.getCardType())
                .build();
        Wallet newWallet = new Wallet();
        newWallet.setCard(newCard);
        newCard.setWallet(newWallet);
        Card card = cardRepository.save(newCard);
        return getCardWalletResponse(card);
    }

    public CardAndWalletResponse getCardById(Integer id) {
        Optional<Card> card = cardRepository.findById(id);
        if (!card.isEmpty()) {
            return getCardWalletResponse(card);
        } else {
            throw new NotFoundException("Card not found with id" + id);
        }
    }
}
