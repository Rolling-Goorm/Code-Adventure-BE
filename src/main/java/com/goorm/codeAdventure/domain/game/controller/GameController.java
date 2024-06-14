package com.goorm.codeAdventure.domain.game.controller;

import com.goorm.codeAdventure.domain.game.dto.response.CategoryResponse;
import com.goorm.codeAdventure.domain.game.dto.response.ProgrammingLanguageResponse;
import com.goorm.codeAdventure.domain.game.dto.response.StageResponse;
import com.goorm.codeAdventure.domain.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @GetMapping("/programmingLanguage")
    public ResponseEntity<List<ProgrammingLanguageResponse>> programmingLanguageList() {
        return ResponseEntity.ok(gameService.findProgrammingLanguage());
    }

    @GetMapping("/programmingLanguage/{programmingLanguageId}/categories/{categoryId}")
    public ResponseEntity<CategoryResponse> categoryDetails(
            @PathVariable Long programmingLanguageId,
            @PathVariable Long categoryId) {
        return ResponseEntity.ok(gameService.findCategory(categoryId));
    }

    @GetMapping("/programmingLanguage/{programmingLanguageId}/categories")
    public ResponseEntity<List<CategoryResponse>> categoryList(
            @PathVariable Long programmingLanguageId
    ) {
        return ResponseEntity.ok(gameService.findCategory());
        // 어차피 언어별로도 카테고리 종류는 똑같아서 사실 pathVariable을 막 쓰지는 않음. 밑의 경우에 씀
        // programmingLanguage가 java인 stages들을 모아서 progress 연산을 통해 해당 카테고리의 총 진척도를 확인할 수 있도록 구현
    }

    @GetMapping("/programmingLanguage/{programmingLanguageId}/categories/{categoryId}/stages")
    public ResponseEntity<List<StageResponse>> stageList(
            @PathVariable Long programmingLanguageId,
            @PathVariable Long categoryId
    ) {
        return ResponseEntity.ok(gameService.findStage(programmingLanguageId, categoryId));
    }
}
