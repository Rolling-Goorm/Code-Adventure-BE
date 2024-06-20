package com.goorm.codeAdventure.domain.problem.compiler;

import com.goorm.codeAdventure.domain.problem.dto.response.CompileResponse;

import javax.tools.*;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URI;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicJavaCompiler implements Compiler {

    @Override
    public CompileResponse compile(String sourceCode, String inputData) {
        // 임의의 클래스 이름 생성
        String className = "Main";

        // 진단 메시지를 수집할 DiagnosticCollector 생성
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

        // 1. JavaCompiler 가져오기
        JavaCompiler compiler = null;
        try {
            compiler = ToolProvider.getSystemJavaCompiler();
            if (compiler == null) {
                throw new IllegalStateException("No compiler found, make sure to run this with a JDK, not a JRE.");
            }

            // 2. 메모리 내 JavaFileObject 생성
            JavaFileObject file = new JavaSourceFromString(className, sourceCode);

            // 3. CompilationTask 생성 컴파일할 파일 목록 설정
            StandardJavaFileManager standardFileManager = compiler.getStandardFileManager(diagnostics, null, null);
            MemoryJavaFileManager fileManager = new MemoryJavaFileManager(standardFileManager);

            Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);
            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits);

            // 4. 소스 코드 컴파일
            LocalDateTime startTime = LocalDateTime.now();
            boolean success = task.call(); // 컴파일러 시작
            if (success) {
                // 5. 컴파일된 클래스를 로드
                Map<String, byte[]> compiledClasses = fileManager.getCompiledClasses();
                MemoryClassLoader memoryClassLoader = new MemoryClassLoader(compiledClasses);
                Class<?> compiledClass = memoryClassLoader.loadClass(className);

                // 컴파일 성공 시 처리할 로직
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                PrintStream printStream = new PrintStream(outputStream);
                PrintStream originalOut = System.out;
                System.setOut(printStream);

                // 입력 스트림 설정
                System.setIn(new ByteArrayInputStream(inputData.getBytes()));

                // 여기서 컴파일된 클래스를 활용할 수 있음 (예: 인스턴스 생성, 메서드 호출 등)
                Object instance = compiledClass.getDeclaredConstructor().newInstance();
                // 예시로 main 메서드 호출
                Method mainMethod = compiledClass.getMethod("main", String[].class);
                String[] mainArgs = new String[]{};
                mainMethod.invoke(instance, (Object) mainArgs);

                System.out.flush();
                System.setOut(originalOut);

                String capturedOutput = outputStream.toString().trim();
                LocalDateTime endTime = LocalDateTime.now();

                return new CompileResponse(success, capturedOutput, Duration.between(startTime, endTime).toMillis()); // 출력된 값을 문자열로 반환하여 사용
            } else {
                // 컴파일 실패 시 진단 메시지들을 문자열로 반환
                List<Diagnostic<? extends JavaFileObject>> diagnosticList = diagnostics.getDiagnostics();
                StringBuilder errorMessage = new StringBuilder();

                for (Diagnostic<? extends JavaFileObject> diagnostic : diagnosticList) {
                    errorMessage.append(diagnostic.getMessage(null)).append("\n");
                }

                return new CompileResponse(success, errorMessage.toString());
            }
        } catch (Exception e) {
            return new CompileResponse(false, e.getMessage());
        }
    }

    // JavaSourceFromString 클래스 정의
    static class JavaSourceFromString extends SimpleJavaFileObject {
        final String code;

        JavaSourceFromString(String name, String code) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return code;
        }
    }

    // 메모리 내 JavaFileManager 클래스 정의
    static class MemoryJavaFileManager extends ForwardingJavaFileManager<StandardJavaFileManager> {
        private final Map<String, ByteArrayOutputStream> compiledClasses = new HashMap<>();

        protected MemoryJavaFileManager(StandardJavaFileManager fileManager) {
            super(fileManager);
        }

        @Override
        public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            compiledClasses.put(className, baos);
            return new SimpleJavaFileObject(URI.create("bytes:///" + className + kind.extension), kind) {
                @Override
                public OutputStream openOutputStream() throws IOException {
                    return baos;
                }
            };
        }

        public Map<String, byte[]> getCompiledClasses() {
            Map<String, byte[]> byteCodeMap = new HashMap<>();
            for (Map.Entry<String, ByteArrayOutputStream> entry : compiledClasses.entrySet()) {
                byteCodeMap.put(entry.getKey(), entry.getValue().toByteArray());
            }
            return byteCodeMap;
        }
    }

    // 메모리 내 ClassLoader 클래스 정의
    static class MemoryClassLoader extends ClassLoader {
        private final Map<String, byte[]> classes;

        public MemoryClassLoader(Map<String, byte[]> classes) {
            this.classes = classes;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] classData = classes.get(name);
            if (classData == null) {
                throw new ClassNotFoundException(name);
            }
            return defineClass(name, classData, 0, classData.length);
        }
    }
}