package com.goorm.codeAdventure.domain.problem.repository;

import com.goorm.codeAdventure.domain.problem.entity.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {
}
