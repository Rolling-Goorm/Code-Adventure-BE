package com.goorm.codeAdventure.domain.game.entity;

import com.goorm.codeAdventure.domain.problem.entity.AttemptResult;
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
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "progresses")
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @Enumerated(EnumType.STRING)
    private AttemptResult attemptResult;

    public Progress(User user, Stage stage, AttemptResult attemptResult) {
        setUser(user);
        setStage(stage);
        this.attemptResult = attemptResult;
    }

    /**
     * 연관관계 설정 관련 메서드
     */
    public void setUser(User user) {
        this.user = user;
        user.getProgresses().add(this);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.getProgresses().add(this);
    }
}

