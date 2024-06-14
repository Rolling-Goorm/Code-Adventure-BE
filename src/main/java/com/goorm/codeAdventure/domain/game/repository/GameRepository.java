package com.goorm.codeAdventure.domain.game.repository;

import com.goorm.codeAdventure.domain.game.entity.Category;
import com.goorm.codeAdventure.domain.game.entity.ProgrammingLanguage;
import com.goorm.codeAdventure.domain.game.entity.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Stage, Long> {
    List<Stage> findByProgrammingLanguageAndCategory(ProgrammingLanguage programmingLanguage, Category category);
}
