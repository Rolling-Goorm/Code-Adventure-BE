package com.goorm.codeAdventure.domain.problem.compiler;

import com.goorm.codeAdventure.domain.problem.dto.response.CompileResponse;

public interface Compiler {
    public CompileResponse compile(String sourceCode, String inputData);
}
