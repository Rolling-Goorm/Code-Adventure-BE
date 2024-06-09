package com.goorm.codeAdventure.problem.domain;

import lombok.Getter;

@Getter
public enum AttemptResult {
    SUCCESS("성공"),
    FAIL("실패");

    private final String message;

    AttemptResult(String message) {
        this.message = message;
    }
}
