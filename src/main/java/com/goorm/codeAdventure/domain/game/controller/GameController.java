package com.goorm.codeAdventure.domain.game.controller;

import com.goorm.codeAdventure.domain.game.dto.response.CategoryResponse;
import com.goorm.codeAdventure.domain.game.dto.response.ProgrammingLanguageResponse;
import com.goorm.codeAdventure.domain.game.dto.response.StageResponse;
import com.goorm.codeAdventure.domain.game.service.GameService;
import com.goorm.codeAdventure.domain.game.service.ProgressService;
import com.goorm.codeAdventure.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;
    private final ProgressService progressService;

    @GetMapping("/programmingLanguage")
    public ResponseEntity<List<ProgrammingLanguageResponse>> programmingLanguageList(
            @SessionAttribute(name="loginUser", required = false) User user
    ) {
        List<ProgrammingLanguageResponse> programmingLanguageResponses = gameService.findProgrammingLanguage();
        List<ProgrammingLanguageResponse> result = programmingLanguageResponses.stream()
                .map(programmingLanguageResponse ->
                        programmingLanguageResponse.toBuilder()
                                .progress(progressService.findProgressByLanguage(user.getId(), programmingLanguageResponse.getId()))
                                .build()
                ).toList();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/programmingLanguage/{programmingLanguageId}/categories/{categoryId}")
    public ResponseEntity<CategoryResponse> categoryDetails(
            @PathVariable Long programmingLanguageId,
            @PathVariable Long categoryId
    ) {
        return ResponseEntity.ok(gameService.findCategory(categoryId));
    }

    @GetMapping("/programmingLanguage/{programmingLanguageId}/categories")
    public ResponseEntity<List<CategoryResponse>> categoryList(
            @PathVariable Long programmingLanguageId,
            @SessionAttribute(name="loginUser", required = false) User user
    ) {
        List<CategoryResponse> categoryResponses = gameService.findCategory();
        List<Double> progresses = progressService.findProgressByCategory(user.getId(), programmingLanguageId);

        List<CategoryResponse> result = IntStream.range(0, categoryResponses.size())
                .mapToObj(index -> categoryResponses.get(index).toBuilder()
                        .progress(progresses.get(index))
                        .build()
                )
                .toList();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/programmingLanguage/{programmingLanguageId}/categories/{categoryId}/stages")
    public ResponseEntity<List<StageResponse>> stageList(
            @PathVariable Long programmingLanguageId,
            @PathVariable Long categoryId
    ) {
        return ResponseEntity.ok(gameService.findStage(programmingLanguageId, categoryId));
    }
}
