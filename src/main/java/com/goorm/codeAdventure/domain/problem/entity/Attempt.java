package com.goorm.codeAdventure.domain.problem.entity;

import com.goorm.codeAdventure.domain.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Attempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private AttemptResult result; // 예: Pass, Fail000

    private String submittedCode;

    private LocalDateTime submitTime;

    private Integer remainingAttemptCount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
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
}
