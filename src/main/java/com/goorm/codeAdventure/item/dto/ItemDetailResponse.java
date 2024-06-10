package com.goorm.codeAdventure.item.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class ItemDetailResponse {
    private final Long id;
    private final String name;
    private final Integer stockQuantity;
    private final Integer price;
    //




}
