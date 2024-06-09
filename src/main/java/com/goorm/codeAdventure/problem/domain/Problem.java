package com.goorm.codeAdventure.problem.domain;

import com.goorm.codeAdventure.game.domain.Stage;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Table(name = "problems")
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String problemDescription;

    @Lob
    private String inputDescription;

    @Lob
    private String outputDescription;

    @OneToOne
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @OneToMany(mappedBy = "problem")
    private List<IoExample> ioExamples;

    @OneToMany(mappedBy = "problem")
    private Set<SupportedLanguage> supportedLanguages = new HashSet<>();

    @OneToMany(mappedBy = "problem")
    private List<Attempt> attempts = new ArrayList<>();

    @Entity
    @Getter
    @Table(name = "io_examples")
    public static class IoExample {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String input;

        private String output;

        @ManyToOne
        @JoinColumn(name = "problem_id")
        private Problem problem;

        /**
         * 연관관계 설정 관련 메서드
         */
        public void setProblem(Problem problem) {
            this.problem = problem;
            problem.getIoExamples().add(this);
        }
    }
}
