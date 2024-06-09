package com.goorm.codeAdventure.game.domain;

import com.goorm.codeAdventure.problem.domain.Problem;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "stages")
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String level; // 예: Easy, Medium, Hard

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(mappedBy = "stage")
    private Problem problem;

    @OneToMany(mappedBy = "stage")
    private List<Progress> progresses = new ArrayList<>();

    /**
     * 연관관계 설정 관련 메서드
     */
    public void setCategory(Category category) {
        this.category = category;
        category.getStages().add(this);
    }
}
