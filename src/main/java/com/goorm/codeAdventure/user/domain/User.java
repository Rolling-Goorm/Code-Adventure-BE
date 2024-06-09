package com.goorm.codeAdventure.user.domain;

import com.goorm.codeAdventure.game.domain.Progress;
import com.goorm.codeAdventure.problem.domain.Attempt;
import com.goorm.codeAdventure.problem.domain.ProgrammingLanguage;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;

    private String loginPassword;

    private String name;

    private String nickname;

    @OneToOne
    private ProgrammingLanguage preferredLanguage;

    private LocalDate birth;

    private String email;

    private String phoneNumber;

    private int coin;

    @OneToMany(mappedBy = "user")
    private List<Attempt> attempts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Progress> progresses = new ArrayList<>();
}
