package com.goorm.codeAdventure.domain.problem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SolveResponse {
    private boolean isSuccess;

    private Integer remainingLife;

    private Integer runtime;

    private String syntaxError;

    private Integer rewardCoin;
}
