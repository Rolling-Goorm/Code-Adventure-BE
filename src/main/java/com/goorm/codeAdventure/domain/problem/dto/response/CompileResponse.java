package com.goorm.codeAdventure.domain.problem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class CompileResponse {
    @Setter
    private boolean success;

    private String compileResult;

    private long submitTime;

    public CompileResponse(boolean success, String compileResult) {
        this.success = success;
        this.compileResult = compileResult;
    }

    public CompileResponse() {

    }

}
