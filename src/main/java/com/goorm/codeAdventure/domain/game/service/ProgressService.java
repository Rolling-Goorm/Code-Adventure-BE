package com.goorm.codeAdventure.domain.game.service;

import com.goorm.codeAdventure.domain.game.entity.Category;
import com.goorm.codeAdventure.domain.game.entity.ProgrammingLanguage;
import com.goorm.codeAdventure.domain.game.repository.GameRepository;
import com.goorm.codeAdventure.domain.game.repository.ProgressRepository;
import com.goorm.codeAdventure.domain.problem.entity.AttemptResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProgressService {
    private final ProgressRepository progressRepository;
    private final GameRepository gameRepository;

    /**
     * 특정 언어의 진행도 조회
     */
    public Double findProgressByLanguage(Long userId, Long languageId) {
        double averageProgress = findProgressByCategory(userId, languageId).stream()
                .mapToDouble(Double::doubleValue) // double 기본 타입으로 변환
                .average() // 평균 계산
                .orElseThrow(() -> new IllegalStateException("진행률을 계산할 수 없습니다."));

        BigDecimal bd = BigDecimal.valueOf(averageProgress);
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }


    /**
     * 특정 카테고리의 진행도 조회
     */
    public List<Double> findProgressByCategory(Long userId, Long languageId) {
        List<Category> categories = Category.findAll(); // 모든 카테고리를 조회
        ProgrammingLanguage programmingLanguage = ProgrammingLanguage.findById(languageId);

        List<Double> list = categories.stream()
                .map(category -> calculateProgress(userId, programmingLanguage, category))
                .toList();
        list.forEach(System.out::println);

        return list;

//        return categories.stream()
//                .map(category -> calculateProgress(userId, programmingLanguage, category))
//                .toList(); // 스트림의 결과를 리스트로 수집
    }

    /**
     * 주어진 카테고리와 프로그래밍 언어에 대해 사용자의 진행률을 계산합니다.
     */
    private Double calculateProgress(Long userId, ProgrammingLanguage programmingLanguage, Category category) {
        Integer successfulStagesCount = progressRepository.countSuccessfulStagesByCategory(
                userId, programmingLanguage, category, AttemptResult.SUCCESS
        );
        Integer allStagesCount = gameRepository.countByProgrammingLanguageAndCategory(programmingLanguage, category);

        if (allStagesCount == 0) {
            return 0.0; // 스테이지가 없는 경우 진행률 0으로 처리
        }

        double progress = (double) successfulStagesCount / allStagesCount * 100;
        BigDecimal bd = BigDecimal.valueOf(progress);
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }
}
