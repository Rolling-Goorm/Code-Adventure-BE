package com.goorm.codeAdventure.domain.problem.dto.response;

import com.goorm.codeAdventure.domain.problem.entity.Problem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IoExampleResponse {
    private Long id;

    private String input;

    private String output;

    public static IoExampleResponse of(Problem.IoExample ioExample) {
        IoExampleResponse response = new IoExampleResponse();

        response.id = ioExample.getId();
        response.input = ioExample.getInput();
        response.output = ioExample.getOutput();

        return response;
    }
}
