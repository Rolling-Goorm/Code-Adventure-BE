package com.goorm.codeAdventure.domain.problem.repository;

import com.goorm.codeAdventure.domain.problem.entity.Attempt;
import com.goorm.codeAdventure.domain.problem.entity.AttemptResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {
    // 특정 문제의 시도 중에서 특정 사용자가 몇 번 실패했는지에 따라 차감된 라이프를 반환
    @Query("select count(a) from Attempt a where a.problem.id = ?1 and a.user.id = ?2 and a.result = ?3")
    long countFailedAttempt(Long problemId, Long userId, AttemptResult result);
}
