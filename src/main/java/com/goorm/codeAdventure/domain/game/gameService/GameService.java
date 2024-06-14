package com.goorm.codeAdventure.domain.game.gameService;

import com.goorm.codeAdventure.domain.game.compiler.DynamicJavaCompiler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.List;

@Service
@Transactional
public class GameService {

    private static final Logger logger = LoggerFactory.getLogger(GameService.class);

    public void compile(String language, String sourceCode) {
        if (language.equals("java")) {
            compileJava(sourceCode);
        }
    }

    public String compileJava(String sourceCode) {
        DynamicJavaCompiler dynamicJavaCompiler = new DynamicJavaCompiler();
        try {
            // 진단 메시지를 수집할 DiagnosticCollector 생성
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
            DynamicJavaCompiler.CompiledResult result = dynamicJavaCompiler.compile(sourceCode, diagnostics);
            if (result.isSuccess()) {
                // 컴파일 성공 시 처리할 로직
                Class<?> compiledClass = result.getCompiledClass();
                // 출력을 캡쳐하려는 작업
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                PrintStream printStream = new PrintStream(outputStream);
                PrintStream originalOut = System.out;
                System.setOut(printStream);

                // 여기서 컴파일된 클래스를 활용할 수 있음 (예: 인스턴스 생성, 메서드 호출 등)
                Object instance = compiledClass.getDeclaredConstructor().newInstance();
                // 예시로 main 메서드 호출
                Method mainMethod = compiledClass.getMethod("main", String[].class);
                String[] mainArgs = new String[]{};
                mainMethod.invoke(instance, (Object) mainArgs);

                System.out.flush();
                System.setOut(originalOut);

                String capturedOutput = outputStream.toString().trim();
                return capturedOutput; // 출력된 값을 문자열로 반환하여 사용

            } else {
                // 컴파일 실패 시 진단 메시지들을 문자열로 반환
                List<Diagnostic<? extends JavaFileObject>> diagnosticList = diagnostics.getDiagnostics();
                StringBuilder errorMessage = new StringBuilder();
                errorMessage.append("Compilation failed for source code:\n");
                errorMessage.append(sourceCode).append("\n\n");

                for (Diagnostic<? extends JavaFileObject> diagnostic : diagnosticList) {
                    errorMessage.append(diagnostic.getMessage(null)).append("\n");
                }

//                logger.error(errorMessage.toString());
                return errorMessage.toString();
            }
        } catch (Exception e) {
//            logger.error("Exception occurred during compilation for source code: {}", sourceCode, e);
            return "Exception occurred during compilation: " + e.getMessage();
        }
    }

    // 컴파일 실패 시 발생할 예외 클래스 정의
    public static class CompilationException extends RuntimeException {
        public CompilationException(String message) {
            super(message);
        }

        public CompilationException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static void main(String[] args) {
        String code = """
                    public static void main(String[] args) {
                                System.out.println("$$$$$$$$$$$$$$$$$$$")
                    }
            """;
        String language = "java";

        GameService gameService = new GameService();
        String str = gameService.compileJava(code);
        System.out.println("----------------------");
        System.out.println("str = " + str);
    }
}