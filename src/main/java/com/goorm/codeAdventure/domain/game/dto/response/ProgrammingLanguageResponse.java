package com.goorm.codeAdventure.domain.game.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class ProgrammingLanguageResponse {
    private Long id;

    private String name;

    private Double progress;
}
