package com.goorm.codeAdventure.domain.game.repository;

import com.goorm.codeAdventure.domain.game.entity.Category;
import com.goorm.codeAdventure.domain.game.entity.ProgrammingLanguage;
import com.goorm.codeAdventure.domain.game.entity.Progress;
import com.goorm.codeAdventure.domain.problem.entity.AttemptResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    @Query("""
            select count(p)
            from Progress p
            where p.user.id = ?1 and p.stage.programmingLanguage = ?2 and p.stage.category = ?3 and p.attemptResult = ?4
            """)
    Integer countStagesByCategory(
            Long userId,
            ProgrammingLanguage programmingLanguage,
            Category category,
            AttemptResult attemptResult
    );

    // 사용자가 특정 문제를 푼 적이 있는지 확인 -> 있으면 코인 안줘버려
    @Query("""
            select (count(p) > 0) from Progress p
            where p.user.id = ?1 and p.stage.problem.id = ?2 and p.attemptResult = ?3
            """)
    boolean isProgressExistByProblem(Long userId, Long problemId, AttemptResult attemptResult);
}
