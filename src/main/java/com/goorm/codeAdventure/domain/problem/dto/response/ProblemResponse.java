package com.goorm.codeAdventure.domain.problem.dto.response;

import com.goorm.codeAdventure.domain.problem.entity.Problem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ProblemResponse {
    private Long id;

    private String title;

    private String problemDescription;

    private String inputDescription;

    private String outputDescription;

    private List<IoExampleResponse> ioExamples;

    public ProblemResponse(Problem problem) {
        ProblemResponse.builder()
                .id(problem.getId())
                .title(problem.getTitle())
                .problemDescription(problem.getProblemDescription())
                .inputDescription(problem.getInputDescription())
                .outputDescription(problem.getOutputDescription())
                .ioExamples(
                        problem.getIoExamples()
                                .stream()
                                .map(IoExampleResponse::new)
                                .toList()
                )
                .build();
    }
}
