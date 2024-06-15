package com.goorm.codeAdventure.domain.problem.entity;

import com.goorm.codeAdventure.domain.game.entity.Stage;
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
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
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
    private List<IoExample> ioExamples = new ArrayList<>();

    @OneToMany(mappedBy = "problem")
    private List<Attempt> attempts = new ArrayList<>();

    public Problem(
            String title,
            String problemDescription,
            String inputDescription,
            String outputDescription,
            Stage stage
    ) {
        this.title = title;
        this.problemDescription = problemDescription;
        this.inputDescription = inputDescription;
        this.outputDescription = outputDescription;
        this.stage = stage;
    }

    @Entity
    @Getter
    @NoArgsConstructor
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

        public IoExample(Problem problem, String input, String output) {
            setProblem(problem);
            this.input = input;
            this.output = output;
        }

        /**
         * 연관관계 설정 관련 메서드
         */
        public void setProblem(Problem problem) {
            this.problem = problem;
            problem.getIoExamples().add(this);
        }
    }
}
