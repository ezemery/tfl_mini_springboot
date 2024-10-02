package com.ezechukwu.tfl.services;

import com.ezechukwu.tfl.dto.response.CardAndWalletResponse;
import com.ezechukwu.tfl.dto.response.CardResponse;

public interface CardService {
    CardAndWalletResponse registerCard(CardResponse request);
    CardAndWalletResponse getCardById(Integer id);
}
