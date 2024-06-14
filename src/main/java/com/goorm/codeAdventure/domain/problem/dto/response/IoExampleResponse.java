package com.goorm.codeAdventure.domain.problem.dto.response;

import com.goorm.codeAdventure.domain.problem.entity.Problem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class IoExampleResponse {
    private Long id;

    private String input;

    private String output;

    public IoExampleResponse(Problem.IoExample ioExample) {
        IoExampleResponse.builder()
                .id(ioExample.getId())
                .input(ioExample.getInput())
                .output(ioExample.getOutput())
                .build();
    }
}
