package com.goorm.codeAdventure.domain.game.dto.response;

import com.goorm.codeAdventure.domain.game.entity.Category;
import com.goorm.codeAdventure.domain.game.entity.ProgrammingLanguage;
import com.goorm.codeAdventure.domain.game.entity.Stage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StageResponse {
    private Long id;

    private String level;

    private ProgrammingLanguage programmingLanguage;

    private Category category;


//    private String attemptResult;

    public static StageResponse of(Stage stage) {
        StageResponse stageResponse = new StageResponse();

        stageResponse.id = stage.getId();
        stageResponse.level = stage.getLevel();
        stageResponse.programmingLanguage = stage.getProgrammingLanguage();
        stageResponse.category = stage.getCategory();

        return stageResponse;
    }
}
