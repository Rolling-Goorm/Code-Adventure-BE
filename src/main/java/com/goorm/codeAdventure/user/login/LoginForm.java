package com.goorm.codeAdventure.user.login;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {


    @NotEmpty(message = "로그인 아이디는 필수 입니다.")
    private String loginId;

    @NotEmpty(message = "비밀번호는 필수 입니다.")
    private String loginPassword;

}
