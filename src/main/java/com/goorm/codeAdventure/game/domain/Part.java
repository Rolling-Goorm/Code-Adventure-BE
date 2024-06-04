package com.goorm.codeAdventure.game.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "parts")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;

    @OneToMany(mappedBy = "part")
    private List<Stage> stages;

    @Entity
    @Getter
    @Table(name = "stages")
    public static class Stage {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String stageDescription;

        private String inputDescription;

        private String outputDescription;

        private String inputExample;

        private String outputExample;

        private int rewardCoin;

        @ManyToOne(fetch = FetchType.LAZY)
        private Part part;
    }
}
