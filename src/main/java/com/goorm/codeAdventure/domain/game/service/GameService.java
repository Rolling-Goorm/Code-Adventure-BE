package com.goorm.codeAdventure.domain.game.service;

import com.goorm.codeAdventure.domain.game.compiler.DynamicJavaCompiler;
import com.goorm.codeAdventure.domain.game.compiler.JavaScriptExecutor;
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
        ProgrammingLanguage programmingLanguage = ProgrammingLanguage.findById(programmingLanguageId);

        return ProgrammingLanguageResponse.builder()
                .id(programmingLanguage.getId())
                .name(programmingLanguage.getName())
                .build();
    }

    public List<ProgrammingLanguageResponse> findProgrammingLanguage() {
        return ProgrammingLanguage.findAll()
                .stream()
                .map(programmingLanguage -> ProgrammingLanguageResponse.builder()
                        .id(programmingLanguage.getId())
                        .name(programmingLanguage.getName())
                        .build()
                )
                .toList();
    }

    /**
     * 카테고리
     */
    public CategoryResponse findCategory(Long categoryId) {
        Category category = Category.findById(categoryId);

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public List<CategoryResponse> findCategory() {
        return Category.findAll()
                .stream()
                .map(category -> CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build()
                )
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

    /**
     * 자바, 자바스크립트 컴파일러
     */
    public void compile(String language, String sourceCode) throws Exception {
        if (language.equals("java")) {
            DynamicJavaCompiler dynamicJavaCompiler = new DynamicJavaCompiler();
            String result = dynamicJavaCompiler.compileJava(sourceCode);
            System.out.println(result);
        } else if (language.equals("javascript")) {
            JavaScriptExecutor javaScriptExecutor = new JavaScriptExecutor();
            String result = javaScriptExecutor.executeJavaScript(sourceCode);
            System.out.println(result);
        }
    }
}
