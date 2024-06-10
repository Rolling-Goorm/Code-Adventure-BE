package com.goorm.codeAdventure.domain.problem.entity;

import lombok.Getter;

@Getter
public enum AttemptResult {
    SUCCESS("성공"),
    FAIL("실패"),
    NOT_ATTEMPTED("미시도");

    private final String message;

    AttemptResult(String message) {
        this.message = message;
    }
}
