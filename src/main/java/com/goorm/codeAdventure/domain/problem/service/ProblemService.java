package com.goorm.codeAdventure.domain.problem.service;

import com.goorm.codeAdventure.domain.problem.compiler.Compiler;
import com.goorm.codeAdventure.domain.problem.compiler.CompileResult;
import com.goorm.codeAdventure.domain.problem.compiler.DynamicJavaCompiler;
import com.goorm.codeAdventure.domain.problem.compiler.JavaScriptExecutor;
import com.goorm.codeAdventure.domain.problem.dto.response.ProblemResponse;
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

    //     문제 단건 조회(id에 따른)
    public ProblemResponse findProblem(Long problemId) {
        return ProblemResponse.of(
                problemRepository.findById(problemId)
                        .orElseThrow(() -> new IllegalArgumentException("해당 문제가 존재하지 않습니다."))
        );
    }

    /**
     * 자바, 자바스크립트 컴파일러
     */
    public void solveCode(Long problemId, String sourceCode, User user) throws Exception {
        Problem problem = problemRepository.findById(problemId).get();
        String language = problem.getStage().getProgrammingLanguage().getName();

        CompileResult compileResult = compileCode(problemId, language, sourceCode);

        // 컴파일한 결과를 가지고 시도횟수 차감 혹은 코인지급
        if (compileResult.isSuccess()) {
            attemptRepository.save(new Attempt(AttemptResult.SUCCESS, sourceCode, compileResult.getSubmitTime(), user, problem));

            // 코인 지급을 했는지 안했는지
            if (true) {
                user.setCoin(
                        user.getCoin() + 100
                );
            }
        } else {
            attemptRepository.save(new Attempt(AttemptResult.FAIL, sourceCode, compileResult.getSubmitTime(), user, problem));
        }
    }

    private CompileResult compileCode(Long problemId, String language, String sourceCode) {
        CompileResult compileResult = new CompileResult();
        Compiler compiler;

        // 컴파일할 때의 입력 데이터를 매개변수로 전달
        List<Problem.IoExample> ioExamples = problemRepository.findById(problemId).get().getIoExamples();

        // 언어에 따른 컴파일러 객체 생성
        if (language.equals("JAVA")) {
            compiler = new DynamicJavaCompiler();
        } else if (language.equals("JAVASCRIPT")) {
            compiler = new JavaScriptExecutor();
        } else {
            return new CompileResult(false, "해당하는 언어가 없습니다.");
        }

        // 입력에 따른 컴파일러 실행
        for (Problem.IoExample ioExample : ioExamples) {
            compileResult = compiler.compile(sourceCode, ioExample.getInput());
            if (!compileResult.isSuccess()) {
                break;
            }
            // input의 컴파일 내용과 output 문자열 비교
            if (!compileResult.getCompileResult().equals(ioExample.getOutput())) {
                // compileResult의 boolean값을 false로 반환
                compileResult.setSuccess(false);
                break;
            }
        }
        return compileResult;
    }

}
