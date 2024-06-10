package com.goorm.codeAdventure.domain.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPurchaseRequest {
    private  Integer quantity;//Jackson 라이브러리가 만들어주니까 => 값을 넣어줌.

}
