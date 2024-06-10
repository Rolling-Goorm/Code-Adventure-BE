package com.goorm.codeAdventure.game.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Language language;

    @OneToMany(mappedBy = "game")
    private List<Part> parts;
}
