package com.goorm.codeAdventure.domain.problem.service;

import com.goorm.codeAdventure.domain.problem.dto.response.ProblemResponse;
import com.goorm.codeAdventure.domain.problem.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProblemService {
    private final ProblemRepository problemRepository;

    // 문제 단건 조회(id에 따른)
    public ProblemResponse findProblem(Long problemId) {
        return ProblemResponse.of(
                problemRepository.findById(problemId)
                        .orElseThrow(() -> new IllegalArgumentException("해당 문제가 존재하지 않습니다."))
        );
    }
}
