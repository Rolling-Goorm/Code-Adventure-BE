package com.goorm.codeAdventure.domain.user.entity;


import com.goorm.codeAdventure.domain.problem.entity.Attempt;
import com.goorm.codeAdventure.domain.problem.entity.ProgrammingLanguage;
import com.goorm.codeAdventure.domain.user.dto.request.UserForm;
import com.goorm.codeAdventure.domain.game.entity.Progress;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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

    @Setter
    private Integer coin;

    public User() {
    }

    public User(String loginId, String loginPassword, String name, String nickname, ProgrammingLanguage preferredLanguage, LocalDate birth, String email, String phoneNumber) {
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.name = name;
        this.nickname = nickname;
        this.preferredLanguage = preferredLanguage;
        this.birth = birth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.coin = 0;
    }

    public void updateUser(UserForm updateUser) {
        this.loginId = updateUser.getLoginId();
        this.loginPassword = updateUser.getLoginPassword();
        this.name = updateUser.getName();
        this.nickname = updateUser.getNickname();
        this.preferredLanguage = updateUser.getPreferredLanguage();
        this.birth = updateUser.getBirth();
        this.email = updateUser.getEmail();
        this.phoneNumber = updateUser.getPhoneNumber();
    }

    @OneToMany(mappedBy = "user")
    private List<Attempt> attempts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Progress> progresses = new ArrayList<>();
}
