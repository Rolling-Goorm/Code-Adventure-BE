package com.goorm.codeAdventure.domain.item.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
