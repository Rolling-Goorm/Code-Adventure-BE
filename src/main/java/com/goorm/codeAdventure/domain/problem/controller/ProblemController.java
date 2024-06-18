package com.goorm.codeAdventure.domain.problem.controller;

import com.goorm.codeAdventure.domain.problem.dto.response.ProblemResponse;
import com.goorm.codeAdventure.domain.problem.service.ProblemService;
import com.goorm.codeAdventure.domain.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProblemController {
    private final ProblemService problemService;

    @GetMapping("/problems/{problemId}")
    public ResponseEntity<ProblemResponse> problemDetails(@PathVariable Long problemId) {
        return ResponseEntity.ok(problemService.findProblem(problemId));
    }

    @PostMapping("/problems/{problemId}")
    public ResponseEntity<String> solveProblem(@PathVariable Long problemId, @RequestBody String sourceCode,
                                               @SessionAttribute(name = "loginUser", required = false) User user) throws Exception {
        problemService.solveCode(problemId, sourceCode, user);

        return ResponseEntity.ok("");
    }
}
