package com.goorm.codeAdventure.game.domain;

import com.goorm.codeAdventure.problem.domain.AttemptResult;
import com.goorm.codeAdventure.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "progresses")
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "stage_id")
    private Stage stage;

    private AttemptResult attemptResult;

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

