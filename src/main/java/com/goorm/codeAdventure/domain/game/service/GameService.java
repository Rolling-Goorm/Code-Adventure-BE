package com.goorm.codeAdventure.domain.game.service;

import com.goorm.codeAdventure.domain.game.dto.response.CategoryResponse;
import com.goorm.codeAdventure.domain.game.dto.response.ProgrammingLanguageResponse;
import com.goorm.codeAdventure.domain.game.dto.response.StageResponse;
import com.goorm.codeAdventure.domain.game.entity.Category;
import com.goorm.codeAdventure.domain.game.entity.ProgrammingLanguage;
import com.goorm.codeAdventure.domain.game.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GameService {
    private final GameRepository gameRepository;

    /**
     * 프로그래밍 언어
     */
    public ProgrammingLanguageResponse findProgrammingLanguage(Long programmingLanguageId) {
        return ProgrammingLanguageResponse.of(
                ProgrammingLanguage.findById(programmingLanguageId)
        );
    }

    public List<ProgrammingLanguageResponse> findProgrammingLanguage() {
        return ProgrammingLanguage.findAll()
                .stream()
                .map(ProgrammingLanguageResponse::of)
                .toList();
    }

    /**
     * 카테고리
     */
    public CategoryResponse findCategory(Long categoryId) {
        return CategoryResponse.of(
                Category.findById(categoryId)
        );
    }

    public List<CategoryResponse> findCategory() {
        return Category.findAll()
                .stream()
                .map(CategoryResponse::of)
                .toList();
    }

    /**
     * 스테이지 조회
     */
    public StageResponse findStage(Long stageId) {
        return StageResponse.of(
                gameRepository.findById(stageId)
                        .orElseThrow(() -> new IllegalArgumentException("스테이지를 찾을 수 없습니다."))
        );
    }

    public List<StageResponse> findStage(Long programmingLanguageId, Long categoryId) {
        ProgrammingLanguage programmingLanguage = ProgrammingLanguage.findById(programmingLanguageId);
        Category category = Category.findById(categoryId);

        return gameRepository.findByProgrammingLanguageAndCategory(programmingLanguage, category)
                .stream()
                .map(StageResponse::of)
                .toList();
    }
}