package com.goorm.codeAdventure.domain.game.entity;

import com.goorm.codeAdventure.domain.problem.entity.Problem;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "stages")
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String level; // 예: Easy, Medium, Hard

    @Enumerated(EnumType.STRING)
    private ProgrammingLanguage programmingLanguage;

    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToOne(mappedBy = "stage")
    private Problem problem;

    @OneToMany(mappedBy = "stage")
    private List<Progress> progresses = new ArrayList<>();

    public Stage(ProgrammingLanguage programmingLanguage, Category category, String level) {
        this.programmingLanguage = programmingLanguage;
        this.category = category;
        this.level = level;
    }
}
