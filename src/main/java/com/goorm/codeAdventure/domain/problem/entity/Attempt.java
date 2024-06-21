package com.goorm.codeAdventure.domain.problem.entity;

import com.goorm.codeAdventure.domain.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Attempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private AttemptResult result; // 예: Pass, Fail000

    private String submittedCode;

    private long submitTime; // long 타입으로 변환

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    /**
     * 연관관계 설정 관련 메서드
     */
    public void setUser(User user) {
        this.user = user;
        user.getAttempts().add(this);
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
        problem.getAttempts().add(this);
    }

    public Attempt(AttemptResult result, String submittedCode, long submitTime, User user, Problem problem) {
        this.result = result;
        this.submittedCode = submittedCode;
        this.submitTime = submitTime;
        this.user = user;
        this.problem = problem;
    }
}
