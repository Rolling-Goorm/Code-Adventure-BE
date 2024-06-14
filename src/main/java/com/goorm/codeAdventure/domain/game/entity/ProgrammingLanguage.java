package com.goorm.codeAdventure.domain.game.entity;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum ProgrammingLanguage {
    JAVA(1L, "JAVA"),
    JAVASCRIPT(2L, "JAVASCRIPT");

    private final Long id;
    private final String name;

    ProgrammingLanguage(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ProgrammingLanguage findById(Long id) {
        return Arrays.stream(ProgrammingLanguage.values())
                .filter(programmingLanguage -> programmingLanguage.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 카테고리가 없습니다."));
    }

    public static List<ProgrammingLanguage> findAll() {
        return Arrays.asList(ProgrammingLanguage.values());
    }
}
