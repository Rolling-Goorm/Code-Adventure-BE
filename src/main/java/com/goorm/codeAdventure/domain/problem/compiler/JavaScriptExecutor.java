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
            // Java 데이터를 JavaScript에 전달
            context.getBindings("js").putMember("inputData", inputData);

            // JavaScript 코드 실행
            LocalDateTime startTime = LocalDateTime.now();
            String script = "var input = inputData;\n" + sourceCode;
            Value result = context.eval("js", script);

            // 결과를 문자열로 반환
            LocalDateTime endTime = LocalDateTime.now();

            return new CompileResult(true, result.toString(), Duration.between(startTime, endTime).toMillis());
        } catch (Exception e) {
            // 예외 발생 시 오류 메시지를 반환
            return new CompileResult(false, e.getMessage());

        }
    }
}
