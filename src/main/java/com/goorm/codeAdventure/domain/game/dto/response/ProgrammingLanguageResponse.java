package com.goorm.codeAdventure.domain.game.dto.response;

import com.goorm.codeAdventure.domain.game.entity.ProgrammingLanguage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProgrammingLanguageResponse {
    private Long id;

    private String name;

    public static ProgrammingLanguageResponse of(ProgrammingLanguage programmingLanguage) {
        ProgrammingLanguageResponse programmingLanguageResponse = new ProgrammingLanguageResponse();

        programmingLanguageResponse.id = programmingLanguage.getId();
        programmingLanguageResponse.name = programmingLanguage.getName();

        return programmingLanguageResponse;
    }
}
