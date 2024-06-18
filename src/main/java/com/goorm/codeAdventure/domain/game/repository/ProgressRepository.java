package com.goorm.codeAdventure.domain.game.repository;

import com.goorm.codeAdventure.domain.game.entity.Category;
import com.goorm.codeAdventure.domain.game.entity.ProgrammingLanguage;
import com.goorm.codeAdventure.domain.game.entity.Progress;
import com.goorm.codeAdventure.domain.problem.entity.AttemptResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    @Query("select count(p) " +
            "from Progress p " +
            "where p.user.id = ?1 and p.stage.programmingLanguage = ?2 and p.stage.category = ?3 and p.attemptResult = ?4")
    Integer countSuccessfulStagesByCategory(
            Long userId,
            ProgrammingLanguage programmingLanguage,
            Category category,
            AttemptResult attemptResult
    );
}
