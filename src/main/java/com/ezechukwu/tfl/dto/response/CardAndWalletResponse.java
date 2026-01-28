package com.ezechukwu.tfl.dto.response;

import com.ezechukwu.tfl.dto.request.WalletRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardAndWalletResponse {

    private Integer id;
    private String cardName;
    private String cardType;
    private String cardToken;
    private WalletRequest wallet;
}
