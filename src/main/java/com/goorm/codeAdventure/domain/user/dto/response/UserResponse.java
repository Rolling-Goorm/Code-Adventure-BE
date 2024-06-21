package com.goorm.codeAdventure.domain.user.dto.response;

import com.goorm.codeAdventure.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Setter
public class UserResponse {

    private Long id;

    private String loginId;

    private String loginPassword;

    private String name;

    private String nickname;

    private String preferredLanguage;

    private LocalDate birth;

    private String email;

    private String phoneNumber;

    public UserResponse(User findUser) {
        this.id = findUser.getId();
        this.loginId = findUser.getLoginId();
        this.loginPassword = findUser.getLoginPassword();
        this.name = findUser.getName();
        this.nickname = findUser.getNickname();
        this.preferredLanguage = findUser.getPreferredLanguage().getName();
        this.birth = findUser.getBirth();
        this.email = findUser.getEmail();
        this.phoneNumber = findUser.getPhoneNumber();
    }
}
