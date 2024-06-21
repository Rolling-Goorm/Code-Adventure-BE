package com.goorm.codeAdventure.domain.game.controller;

import com.goorm.codeAdventure.domain.game.dto.response.CategoryResponse;
import com.goorm.codeAdventure.domain.game.dto.response.ProgrammingLanguageResponse;
import com.goorm.codeAdventure.domain.game.dto.response.StageResponse;
import com.goorm.codeAdventure.domain.game.service.GameService;
import com.goorm.codeAdventure.domain.game.service.ProgressService;
import com.goorm.codeAdventure.domain.user.dto.response.UserResponse;
import com.goorm.codeAdventure.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;
    private final ProgressService progressService;

    @GetMapping("/programmingLanguage")
    @Operation(summary = "프로그래밍언어 리스트 정보 API", description = "사용 가능한 모든 프로그래밍 언어의 리스트를 반환합니다.")
    public ResponseEntity<List<ProgrammingLanguageResponse>> programmingLanguageList(
            HttpServletRequest request
    ) {
//        HttpSession session = request.getSession(false);
//        if (Objects.isNull(session) || Objects.isNull(session.getAttribute("loginUser"))) {
//            throw new IllegalStateException("로그인이 필요한 서비스입니다.");
//        }
//        UserResponse user = (UserResponse) session.getAttribute("loginUser");

        List<ProgrammingLanguageResponse> programmingLanguageResponses = gameService.findProgrammingLanguage();
        List<ProgrammingLanguageResponse> result = programmingLanguageResponses.stream()
                .map(programmingLanguageResponse ->
                        programmingLanguageResponse.toBuilder()
                                .progress(progressService.findProgressByLanguage(1L, programmingLanguageResponse.getId()))
                                .build()
                ).toList();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/programmingLanguage/{programmingLanguageId}/categories/{categoryId}")
    @Operation(summary = "카테고리 상세 정보 API", description = "특정 프로그래밍 언어의 특정 카테고리에 대한 상세 정보를 반환합니다.")
    public ResponseEntity<CategoryResponse> categoryDetails(
            @PathVariable Long programmingLanguageId,
            @PathVariable Long categoryId
    ) {
        return ResponseEntity.ok(gameService.findCategory(categoryId));
    }

    @GetMapping("/programmingLanguage/{programmingLanguageId}/categories")
    @Operation(summary = "카테고리 리스트 정보 API", description = "특정 프로그래밍 언어의 모든 카테고리 리스트를 반환합니다.")
    public ResponseEntity<List<CategoryResponse>> categoryList(
            @PathVariable Long programmingLanguageId,
            HttpServletRequest request
    ) {
//        HttpSession session = request.getSession(false);
//        if (Objects.isNull(session) || Objects.isNull(session.getAttribute("loginUser"))) {
//            throw new IllegalStateException("로그인이 필요한 서비스입니다.");
//        }
//        UserResponse user = (UserResponse) session.getAttribute("loginUser");

        List<CategoryResponse> categoryResponses = gameService.findCategory();
        List<Double> progresses = progressService.findProgressByCategory(1L, programmingLanguageId);

        List<CategoryResponse> result = IntStream.range(0, categoryResponses.size())
                .mapToObj(index -> categoryResponses.get(index).toBuilder()
                        .progress(progresses.get(index))
                        .build()
                )
                .toList();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/programmingLanguage/{programmingLanguageId}/categories/{categoryId}/stages")
    @Operation(summary = "스테이지 리스트 정보 API", description = "특정 프로그래밍 언어의 특정 카테고리에 속한 모든 스테이지 리스트를 반환합니다.")
    public ResponseEntity<List<StageResponse>> stageList(
            @PathVariable Long programmingLanguageId,
            @PathVariable Long categoryId
    ) {
        return ResponseEntity.ok(gameService.findStage(programmingLanguageId, categoryId));
    }
}
