package com.ezechukwu.tfl.services;

import com.ezechukwu.tfl.exception.NotFoundException;
import com.ezechukwu.tfl.models.Card;
import com.ezechukwu.tfl.models.Charge;
import com.ezechukwu.tfl.records.CardAndWalletRecord;
import com.ezechukwu.tfl.records.ChargeRecord;
import com.ezechukwu.tfl.records.WalletRecord;
import com.ezechukwu.tfl.repositories.CardRepository;
import com.ezechukwu.tfl.repositories.ChargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChargeService {

    @Autowired
    private ChargeRepository chargeRepository;
    @Autowired
    private CardRepository cardRepository;
    public CardAndWalletRecord chargeCard(ChargeRecord chargeCard) {
        Optional<Card> card = cardRepository.findById(chargeCard.card().id());
        if(!card.isEmpty()){
            List<Charge> charge = chargeRepository.findByCardIdAndIsCharged(chargeCard.card().id());
            for(Charge curCharge : charge){
                curCharge.getCard().getWallet().setAvailableBalance(curCharge.getCard().getWallet().getAvailableBalance() - curCharge.getAmount());
                curCharge.getCard().getWallet().setBookBalance(curCharge.getCard().getWallet().getAvailableBalance());
                curCharge.setCharged(true);
                chargeRepository.save(curCharge);
            }
            return new CardAndWalletRecord(card.get().getCardId(),card.get().getCardName(),card.get().getCardType(), card.get().getCardToken(), new WalletRecord(card.get().getWallet().getWalletId(), card.get().getWallet().getAvailableBalance()));
        }else {
            throw new NotFoundException("Card not found with id" + chargeCard.card().id());
        }

    }
}
