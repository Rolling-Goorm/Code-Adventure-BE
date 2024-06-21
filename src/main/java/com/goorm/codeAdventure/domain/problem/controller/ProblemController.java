package com.goorm.codeAdventure.domain.problem.controller;

import com.goorm.codeAdventure.domain.problem.dto.request.SolveRequest;
import com.goorm.codeAdventure.domain.problem.dto.response.ProblemResponse;
import com.goorm.codeAdventure.domain.problem.dto.response.SolveResponse;
import com.goorm.codeAdventure.domain.problem.service.ProblemService;
import com.goorm.codeAdventure.domain.user.entity.User;
import com.goorm.codeAdventure.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ProblemController {
    private final ProblemService problemService;
    private final UserService userService;

    @GetMapping("/problems/{problemId}")
    public ResponseEntity<ProblemResponse> problemDetails(
            @PathVariable Long problemId
    ) {
        User user = userService.findOne(1L);
        return ResponseEntity.ok(problemService.findProblem(problemId, user));
    }

    @PostMapping("/problems/{problemId}")
    public ResponseEntity<SolveResponse> solveProblem(
            @PathVariable Long problemId,
            @RequestBody SolveRequest request
    ) {
        User user = userService.findOne(1L);
        return ResponseEntity.ok(problemService.solveCode(problemId, request.getSourceCode(), user));
    }
}
