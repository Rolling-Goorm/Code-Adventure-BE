package com.goorm.codeAdventure.user.domain;

import com.goorm.codeAdventure.game.domain.Language;
import com.goorm.codeAdventure.user.dto.request.UserForm;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    private String email; // email을 pk

    private String phoneNumber;

    private int coin;

    private int latestStage;

    public User(String loginId, String loginPassword, String name, String nickname, Language prefferedLanguage, LocalDate birth, String email, String phoneNumber) {
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.name = name;
        this.nickname = nickname;
        this.prefferedLanguage = prefferedLanguage;
        this.birth = birth;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void updateUser(UserForm updateUser) {
        this.loginId = updateUser.getLoginId();
        this.loginPassword = updateUser.getLoginPassword();
        this.name = updateUser.getName();
        this.nickname = updateUser.getNickname();
        this.prefferedLanguage = updateUser.getPrefferedLanguage();
        this.birth = updateUser.getBirth();
        this.email = updateUser.getEmail();
        this.phoneNumber = updateUser.getPhoneNumber();
    }

    // 회원정보수정 Form으로 넘어가면 장땡?

}
