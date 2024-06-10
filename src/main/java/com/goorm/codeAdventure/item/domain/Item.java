package com.goorm.codeAdventure.item.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private Integer stockQuantity;
    public boolean isBuyAble()
    {
        return stockQuantity>=1;
    }
    public boolean isnonremain(Integer request)
    {
        return stockQuantity-request<0;
    }

    public Integer minusStock(Integer request)
    {
        stockQuantity-=request;//남은 재고
        return request*price;
    }
}
