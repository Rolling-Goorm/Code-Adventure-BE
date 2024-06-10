package com.goorm.codeAdventure.user.domain;

import com.goorm.codeAdventure.user.dto.request.UserForm;
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

    private String email; // email을 pk

    private String phoneNumber;

    private int coin;

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

    @OneToMany(mappedBy = "user")
    private List<Attempt> attempts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Progress> progresses = new ArrayList<>();
}
