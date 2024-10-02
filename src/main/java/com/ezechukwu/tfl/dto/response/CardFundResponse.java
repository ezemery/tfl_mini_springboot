package com.ezechukwu.tfl.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardFundResponse {
    private Integer cardId;
    private WalletResponse wallet;
}
