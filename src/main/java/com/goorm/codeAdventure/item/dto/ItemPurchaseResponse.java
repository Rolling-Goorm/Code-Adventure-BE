package com.goorm.codeAdventure.item.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ItemPurchaseResponse {
    private final Integer usedCoin;
    private final String  message;

}
