package com.goorm.codeAdventure.domain.problem.controller;

import com.goorm.codeAdventure.domain.problem.dto.response.ProblemResponse;
import com.goorm.codeAdventure.domain.problem.service.ProblemService;
import com.goorm.codeAdventure.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProblemController {
    private final ProblemService problemService;

    @GetMapping("/problems/{problemId}")
    @Operation(summary = "문제 상세 조회 API", description = "특정 문제의 상세 정보를 반환합니다.")
    public ResponseEntity<ProblemResponse> problemDetails(@PathVariable Long problemId) {
        return ResponseEntity.ok(problemService.findProblem(problemId));
    }

    @PostMapping("/problems/{problemId}")
    @Operation(summary = "문제 풀이 결과 API", description = "특정 문제에 대한 풀이 결과를 제출합니다.")
    public ResponseEntity<String> solveProblem(@PathVariable Long problemId, @RequestBody String sourceCode,
                                               @SessionAttribute(name = "loginUser", required = false) User user) throws Exception {
        problemService.solveCode(problemId, sourceCode, user);

        return ResponseEntity.ok("");
    }
}
