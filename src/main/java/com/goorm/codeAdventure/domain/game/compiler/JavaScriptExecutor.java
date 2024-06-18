package com.goorm.codeAdventure.domain.game.compiler;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

public class JavaScriptExecutor {
    public String executeJavaScript(String sourceCode) {
        try (Context context = Context.create()) {
            // JavaScript 코드 실행
            Value result = context.eval("js", sourceCode);

            // 결과를 문자열로 반환
            return result.toString();
        } catch (Exception e) {
            // 예외 발생 시 오류 메시지를 반환
            return "Exception occurred during JavaScript execution: " + e.getMessage();

        }
    }
}
