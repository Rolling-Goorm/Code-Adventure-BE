package com.goorm.codeAdventure.user.controller;

import com.goorm.codeAdventure.game.domain.Language;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserForm {

    @NotEmpty(message = "회원 로그인 아이디는 필수 입니다.")
    private String loginId;

    @NotEmpty(message = "회원 비밀번호는 필수 입니다.")
    private String loginPassword;

    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String name;

    @NotEmpty(message = "별명은 필수 입니다.")
    private String nickname;

    private Language prefferedLanguage;

    private LocalDate birth;

    @NotEmpty(message = "이메일은 필수 입니다.")
    private String email;

    private String phoneNumber;

    private int coin;

    private int latestStage;

}
