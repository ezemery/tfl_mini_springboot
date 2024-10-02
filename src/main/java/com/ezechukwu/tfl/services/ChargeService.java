package com.ezechukwu.tfl.services;

import com.ezechukwu.tfl.dto.response.CardAndWalletResponse;
import com.ezechukwu.tfl.dto.response.ChargeResponse;

public interface ChargeService {
    CardAndWalletResponse chargeCard(ChargeResponse chargeCard);
}
