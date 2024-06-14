package com.goorm.codeAdventure.domain.user.dto.response;

import com.goorm.codeAdventure.domain.problem.entity.ProgrammingLanguage;
import com.goorm.codeAdventure.domain.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserResponse {

    private String loginId;

    private String loginPassword;

    private String name;

    private String nickname;

    private ProgrammingLanguage preferredLanguage;

    private LocalDate birth;

    private String email;

    private String phoneNumber;

    public UserResponse(User findUser) {
        this.loginId = findUser.getLoginId();
        this.loginPassword = findUser.getLoginPassword();
        this.name = findUser.getName();
        this.nickname = findUser.getNickname();
        this.preferredLanguage = findUser.getPreferredLanguage();
        this.birth = findUser.getBirth();
        this.email = findUser.getEmail();
        this.phoneNumber = findUser.getPhoneNumber();
    }
}
