package com.ezechukwu.tfl.services;

import com.ezechukwu.tfl.exception.NotFoundException;
import com.ezechukwu.tfl.models.Card;
import com.ezechukwu.tfl.models.Wallet;
import com.ezechukwu.tfl.records.CardFundRecord;
import com.ezechukwu.tfl.records.WalletRecord;
import com.ezechukwu.tfl.repositories.CardRepository;
import com.ezechukwu.tfl.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private WalletRepository walletRepository;
    public CardFundRecord fundCustomer(CardFundRecord cardFundRequest) {
        Optional<Card> card =  cardRepository.findById(cardFundRequest.card_id());
        if(!card.isEmpty()){
            Wallet updatedWallet = card.get().getWallet();
            updatedWallet.setBookBalance(updatedWallet.getAvailableBalance() + cardFundRequest.wallet().amount());
            updatedWallet.setAvailableBalance(updatedWallet.getAvailableBalance() + cardFundRequest.wallet().amount());
            walletRepository.save(updatedWallet);
            WalletRecord wallet = new WalletRecord(updatedWallet.getWalletId(), updatedWallet.getAvailableBalance());
            return new CardFundRecord(card.get().getCardId(), wallet);
        }else{
            throw new NotFoundException("Card not found with id" + cardFundRequest.card_id());
        }

    }
}
