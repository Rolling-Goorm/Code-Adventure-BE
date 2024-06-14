package com.goorm.codeAdventure.domain.game.entity;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Category {
    IO(1L, "입출력과 사칙연산"),
    CONDITION(2L, "조건문"),
    LOOP(3L, "반복문"),
    ONE_DIMENSION_ARRAY(4L, "1차원 배열"),
    STRING(5L, "문자열"),
    TWO_DIMENSION_ARRAY(6L, "2차원 배열"),
    DEEPEN(7L, "심화"),
    FUNCTION(8L, "함수"),
    MATH(9L, "수학"),
    RECURSION(10L, "재귀");

    private final Long id;
    private final String name;

    Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Category findById(Long id) {
        return Arrays.stream(Category.values())
                .filter(category -> category.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 카테고리가 없습니다."));
    }

    public static List<Category> findAll() {
        return Arrays.asList(Category.values());
    }
}
