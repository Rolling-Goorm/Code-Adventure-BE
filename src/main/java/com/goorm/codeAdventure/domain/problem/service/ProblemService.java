package com.goorm.codeAdventure.domain.problem.service;

import com.goorm.codeAdventure.domain.game.entity.Progress;
import com.goorm.codeAdventure.domain.game.repository.ProgressRepository;
import com.goorm.codeAdventure.domain.problem.compiler.Compiler;
import com.goorm.codeAdventure.domain.problem.compiler.DynamicJavaCompiler;
import com.goorm.codeAdventure.domain.problem.compiler.JavascriptExecutor;
import com.goorm.codeAdventure.domain.problem.dto.response.CompileResponse;
import com.goorm.codeAdventure.domain.problem.dto.response.ProblemResponse;
import com.goorm.codeAdventure.domain.problem.dto.response.SolveResponse;
import com.goorm.codeAdventure.domain.problem.entity.Attempt;
import com.goorm.codeAdventure.domain.problem.entity.AttemptResult;
import com.goorm.codeAdventure.domain.problem.entity.Problem;
import com.goorm.codeAdventure.domain.problem.repository.AttemptRepository;
import com.goorm.codeAdventure.domain.problem.repository.ProblemRepository;
import com.goorm.codeAdventure.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProblemService {
    private final ProblemRepository problemRepository;
    private final AttemptRepository attemptRepository;
    private final ProgressRepository progressRepository;

    //     문제 단건 조회(id에 따른)
    public ProblemResponse findProblem(Long problemId, User user) {
        return ProblemResponse.of(
                problemRepository.findById(problemId)
                        .orElseThrow(() -> new IllegalArgumentException("해당 문제가 존재하지 않습니다.")),
                findRemainingLife(problemId, user)
        );
    }

    /**
     * 자바, 자바스크립트 컴파일러
     */
    @Transactional
    public SolveResponse solveCode(Long problemId, String sourceCode, User user) {
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 문제가 존재하지 않습니다."));
        String language = problem.getStage().getProgrammingLanguage().getName();

        CompileResponse compileResponse = compileCode(problemId, language, sourceCode);
        Integer rewardCoin = 0;
        Integer remainingLife;
        String message = "";

        if (compileResponse.isSuccess()) { // 정답 제출
            Attempt attempt = new Attempt(AttemptResult.SUCCESS, sourceCode, compileResponse.getSubmitTime(), user, problem);
            attemptRepository.save(attempt);

            message = "클리어! 이미 클리어한 스테이지이므로 보상은 지급되지 않습니다.";

            // 아직 성공한 progress 없다면 progress 생성
            if (!progressRepository.isProgressExistByProblem(user.getId(), problemId, AttemptResult.SUCCESS)) {
                Progress progress = new Progress(user, problem.getStage(), AttemptResult.SUCCESS);
                progressRepository.save(progress);

                rewardCoin = 100 * findRemainingLife(problemId, user);
                if (rewardCoin <= 0) rewardCoin = 100;
                user.setCoin(user.getCoin() + rewardCoin);

                message = "최초 클리어! 남은 시도 횟수에 따라 보상이 지급됩니다.";
            }
        }
        if (!compileResponse.isSuccess()) { // 오답 제출
            Attempt attempt = new Attempt(AttemptResult.FAIL, sourceCode, compileResponse.getSubmitTime(), user, problem);
            attemptRepository.save(attempt);

            message = "실패!";

            // 성공한 적이 없고, 아직 실패한 적도 없음 -> 최초로 실패 처리의 progress 생성
            if (!progressRepository.isProgressExistByProblem(user.getId(), problemId, AttemptResult.SUCCESS) &&
                    !progressRepository.isProgressExistByProblem(user.getId(), problemId, AttemptResult.FAIL)) {
                Progress progress = new Progress(user, problem.getStage(), AttemptResult.FAIL);
                progressRepository.save(progress);

                message = "실패!";
            }
        }

        remainingLife = findRemainingLife(problemId, user);

        return SolveResponse.builder()
                .isSuccess(compileResponse.isSuccess())
                .remainingLife(remainingLife)
                .runtime(Math.toIntExact(compileResponse.getSubmitTime()))
                .syntaxError(compileResponse.getCompileResult())
                .rewardCoin(rewardCoin)
                .message(message)
                .build();
    }

    public Integer findRemainingLife(Long problemId, User user) {
        return 3 - Math.toIntExact(
                attemptRepository.countFailedAttempt(problemId, user.getId(), AttemptResult.FAIL)
        );
    }

    private CompileResponse compileCode(Long problemId, String language, String sourceCode) {
        CompileResponse compileResponse = new CompileResponse();
        Compiler compiler;

        // 컴파일할 때의 입력 데이터를 매개변수로 전달
        List<Problem.IoExample> ioExamples = problemRepository.findById(problemId).get().getIoExamples();

        // 언어에 따른 컴파일러 객체 생성
        if (language.equals("JAVA")) {
            compiler = new DynamicJavaCompiler();
        } else if (language.equals("JAVASCRIPT")) {
            compiler = new JavascriptExecutor();
        } else {
            return new CompileResponse(false, "해당하는 언어가 없습니다.");
        }

        // 입력에 따른 컴파일러 실행
        for (Problem.IoExample ioExample : ioExamples) {
            compileResponse = compiler.compile(sourceCode, ioExample.getInput());
            if (!compileResponse.isSuccess()) {
                break;
            }
            // input의 컴파일 내용과 output 문자열 비교
            if (!compileResponse.getCompileResult().equals(ioExample.getOutput())) {
                // compileResult의 boolean값을 false로 반환
                compileResponse.setSuccess(false);
                break;
            }
        }
        return compileResponse;
    }
}