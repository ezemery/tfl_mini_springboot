package com.ezechukwu.tfl.services.impl;

import com.ezechukwu.tfl.exception.NotFoundException;
import com.ezechukwu.tfl.models.Card;
import com.ezechukwu.tfl.models.Wallet;
import com.ezechukwu.tfl.dto.request.CardFundRequest;
import com.ezechukwu.tfl.dto.request.WalletRequest;
import com.ezechukwu.tfl.repositories.CardRepository;
import com.ezechukwu.tfl.repositories.WalletRepository;
import com.ezechukwu.tfl.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private WalletRepository walletRepository;

    public CardFundRequest fundCustomer(CardFundRequest cardFundRequest) {
        Optional<Card> card = cardRepository.findById(cardFundRequest.getCardId());
        if (!card.isEmpty()) {
            Wallet updatedWallet = applyChargeFund(card.get(), cardFundRequest);
            walletRepository.save(updatedWallet);
            WalletRequest wallet = new WalletRequest(updatedWallet.getWalletId(), updatedWallet.getAvailableBalance());
            return new CardFundRequest(card.get().getCardId(), wallet);
        } else {
            throw new NotFoundException("Card not found with id" + cardFundRequest.getCardId());
        }

    }

    private Wallet applyChargeFund(Card card, CardFundRequest cardFundRequest) {
        Wallet updatedWallet = card.getWallet();
        updatedWallet.setBookBalance(updatedWallet.getAvailableBalance() + cardFundRequest.getWallet().getAmount());
        updatedWallet.setAvailableBalance(updatedWallet.getAvailableBalance() + cardFundRequest.getWallet().getAmount());
        return updatedWallet;
    }
}
