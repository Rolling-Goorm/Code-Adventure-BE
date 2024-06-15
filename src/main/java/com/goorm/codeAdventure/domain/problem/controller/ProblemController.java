package com.goorm.codeAdventure.domain.problem.controller;

import com.goorm.codeAdventure.domain.problem.dto.response.ProblemResponse;
import com.goorm.codeAdventure.domain.problem.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ProblemController {
    private final ProblemService problemService;

    @GetMapping("/problems/{problemId}")
    public ResponseEntity<ProblemResponse> problemDetails(@PathVariable Long problemId) {
        return ResponseEntity.ok(problemService.findProblem(problemId));
    }
}
