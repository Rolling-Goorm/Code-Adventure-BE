package com.goorm.codeAdventure.domain.problem.repository;

import com.goorm.codeAdventure.domain.problem.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
}
