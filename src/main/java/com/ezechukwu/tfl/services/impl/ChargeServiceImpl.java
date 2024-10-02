package com.ezechukwu.tfl.services.impl;

import com.ezechukwu.tfl.exception.NotFoundException;
import com.ezechukwu.tfl.models.Card;
import com.ezechukwu.tfl.models.Charge;
import com.ezechukwu.tfl.dto.response.CardAndWalletResponse;
import com.ezechukwu.tfl.dto.response.ChargeResponse;
import com.ezechukwu.tfl.dto.response.WalletResponse;
import com.ezechukwu.tfl.repositories.CardRepository;
import com.ezechukwu.tfl.repositories.ChargeRepository;
import com.ezechukwu.tfl.services.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ezechukwu.tfl.utils.Utils.getCardWalletResponse;

@Service
public class ChargeServiceImpl implements ChargeService {

    @Autowired
    private ChargeRepository chargeRepository;
    @Autowired
    private CardRepository cardRepository;

    public CardAndWalletResponse chargeCard(ChargeResponse chargeCard) {
        Optional<Card> card = cardRepository.findById(chargeCard.getCard().getId());
        if (!card.isEmpty()) {
            List<Charge> charge = chargeRepository.findByCardIdAndIsCharged(chargeCard.getCard().getId());
            for (Charge curCharge : charge) {
                applyDebit(curCharge);
                chargeRepository.save(curCharge);
            }
            return getCardWalletResponse(card);
        } else {
            throw new NotFoundException("Card not found with id" + chargeCard.getCard().getId());
        }
    }

    private void applyDebit(Charge charge) {
        charge.getCard().getWallet().setAvailableBalance(charge.getCard().getWallet().getAvailableBalance() - charge.getAmount());
        charge.getCard().getWallet().setBookBalance(charge.getCard().getWallet().getAvailableBalance());
        charge.setIsCharged(true);
    }
}
