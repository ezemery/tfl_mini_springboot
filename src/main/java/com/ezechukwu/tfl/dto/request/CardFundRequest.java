package com.ezechukwu.tfl.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardFundRequest {
    private Integer cardId;
    private WalletRequest wallet;
}
