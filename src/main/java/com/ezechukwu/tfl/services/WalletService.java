package com.ezechukwu.tfl.services;

import com.ezechukwu.tfl.dto.request.CardFundRequest;

public interface WalletService {
    CardFundRequest fundCustomer(CardFundRequest cardFundRequest);
}
