package com.ezechukwu.tfl.services;

import com.ezechukwu.tfl.exception.NotFoundException;
import com.ezechukwu.tfl.models.Card;
import com.ezechukwu.tfl.models.Wallet;
import com.ezechukwu.tfl.records.CardAndWalletRecord;
import com.ezechukwu.tfl.records.CardRecord;
import com.ezechukwu.tfl.records.WalletRecord;
import com.ezechukwu.tfl.repositories.CardRepository;
import com.ezechukwu.tfl.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private WalletRepository walletRepository;
    public CardAndWalletRecord registerCard(CardRecord request) {
        Card newCard = new Card(request.card_name(), request.card_type(), request.card_token());
        Wallet newWallet = new Wallet();
        newWallet.setCard(newCard);
        newCard.setWallet(newWallet);
        Card card  = cardRepository.save(newCard);
        return new CardAndWalletRecord(card.getCardId(), card.getCardName(), card.getCardType(), card.getCardToken(), new WalletRecord(card.getWallet().getWalletId(), card.getWallet().getAvailableBalance()));
    }

    public CardAndWalletRecord getCardById(Integer id) {
        Optional<Card> card = cardRepository.findById(id);
        if(!card.isEmpty()){
            return new CardAndWalletRecord(card.get().getCardId(),card.get().getCardName(),card.get().getCardType(), card.get().getCardToken(), new WalletRecord(card.get().getWallet().getWalletId(), card.get().getWallet().getAvailableBalance()));
        }else {
            throw new NotFoundException("Card not found with id" + id);
        }
    }
}
