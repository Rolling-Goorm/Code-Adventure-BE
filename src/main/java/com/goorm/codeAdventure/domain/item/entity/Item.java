package com.goorm.codeAdventure.domain.item.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private Integer stockQuantity;

    public Item(String name, Integer price, Integer stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public boolean isBuyAble()
    {
        return stockQuantity>=1;
    }
    public boolean isNonRemain(Integer request)
    {
        return stockQuantity-request<0;
    }

    public Integer minusStock(Integer request)
    {
        stockQuantity-=request;//남은 재고
        return request*price;
    }
}
