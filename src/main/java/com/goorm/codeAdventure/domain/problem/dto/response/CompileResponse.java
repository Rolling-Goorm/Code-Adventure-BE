package com.goorm.codeAdventure.domain.problem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class CompileResponse {
    @Setter
    protected boolean success;
    protected String compileResult;

    protected long submitTime;

    public CompileResponse(boolean success, String compileResult) {
        this.success = success;
        this.compileResult = compileResult;
    }

    public CompileResponse() {

    }

}
