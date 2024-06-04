package com.goorm.codeAdventure.game.domain;

import lombok.Getter;

@Getter
public enum Language {
    JAVA("자바"),
    JAVASCRIPT("자바스크립트");

    private String name;

    Language(String name) {
        this.name = name;
    }
}
