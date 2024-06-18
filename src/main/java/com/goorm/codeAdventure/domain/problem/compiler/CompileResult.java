package com.goorm.codeAdventure.domain.problem.compiler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class CompileResult {
    @Setter
    protected boolean success;
    protected String compileResult;

    protected long submitTime;

    public CompileResult(boolean success, String compileResult) {
        this.success = success;
        this.compileResult = compileResult;
    }

    public CompileResult() {
        this.success = false;
        this.compileResult = "실패했습니다";
    }

}
