package com.ezechukwu.tfl.services;

import com.ezechukwu.tfl.dto.response.CardFundResponse;

public interface WalletService {
    CardFundResponse fundCustomer(CardFundResponse cardFundRequest);
}
