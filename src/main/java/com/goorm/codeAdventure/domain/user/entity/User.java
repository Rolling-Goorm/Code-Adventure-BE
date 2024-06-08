package com.goorm.codeAdventure.domain.user.entity;

import com.goorm.codeAdventure.domain.game.entity.Language;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loginId;

    private String loginPassword;

    private String name;

    private String nickname;

    private Language prefferedLanguage;

    private LocalDate birth;

    private String email;

    private String phoneNumber;

    @Setter
    private Integer coin;

    private Integer latestStage;
}
