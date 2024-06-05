package com.goorm.codeAdventure.user.domain;

import com.goorm.codeAdventure.game.domain.Language;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

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

    private Language prefferedLanguage;

    private LocalDate birth;

    private String email;

    private String phoneNumber;

    private int coin;

    private int latestStage;

    public User() {
    }


    /**
     * 테스트용 생성자
     */
    public User(String nickname) {
        this.nickname = nickname;
    }


    public User(Long id, String loginId, String loginPassword, String name, String nickname, Language prefferedLanguage, LocalDate birth, String email, String phoneNumber) {
        this.id = id;
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.name = name;
        this.nickname = nickname;
        this.prefferedLanguage = prefferedLanguage;
        this.birth = birth;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // 회원정보수정 Form으로 넘어가면 장땡?

}
