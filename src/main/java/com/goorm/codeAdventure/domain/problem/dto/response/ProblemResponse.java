package com.goorm.codeAdventure.domain.problem.dto.response;

import com.goorm.codeAdventure.domain.problem.entity.Problem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProblemResponse {
    private Long id;

    private String title;

    private String problemDescription;

    private String inputDescription;

    private String outputDescription;

    private List<IoExampleResponse> ioExamples;

    public static ProblemResponse of(Problem problem) {
        ProblemResponse response = new ProblemResponse();

        response.id = problem.getId();
        response.title = problem.getTitle();
        response.problemDescription = problem.getProblemDescription();
        response.inputDescription = problem.getInputDescription();
        response.outputDescription = problem.getOutputDescription();
        response.ioExamples = problem.getIoExamples()
                .stream()
                .map(IoExampleResponse::of)
                .toList();

        return response;
    }
}
