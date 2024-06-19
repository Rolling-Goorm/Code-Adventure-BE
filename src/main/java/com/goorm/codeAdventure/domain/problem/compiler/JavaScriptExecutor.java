package com.goorm.codeAdventure.domain.problem.compiler;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Value;

import java.time.Duration;
import java.time.LocalDateTime;

public class JavaScriptExecutor implements Compiler {

    @Override
    public CompileResult compile(String sourceCode, String inputData) {
        try (Context context = Context.newBuilder("js")
                .allowHostAccess(HostAccess.ALL)
                .build()) {

            LocalDateTime startTime = LocalDateTime.now();
            // Java 데이터를 JavaScript에 전달
            context.getBindings("js").putMember("inputData", inputData);
            context.getBindings("js").putMember("capturedOutput", "");

            // console.log를 가로채기 위한 재정의
            context.eval("js", "console.log = function(message) { capturedOutput += message + '\\n'; };");

            // JavaScript 코드 평가 (solution 함수 정의)
            context.eval("js", sourceCode);

            // JavaScript 코드 실행
            Value function = context.getBindings("js").getMember("solution");
            Value result = function.execute();

            // 결과를 문자열로 반환
            LocalDateTime endTime = LocalDateTime.now();

            return new CompileResult(true, result.toString(), Duration.between(startTime, endTime).toMillis());
        } catch (Exception e) {
            // 예외 발생 시 오류 메시지를 반환
            return new CompileResult(false, e.getMessage());
        }
    }
}
