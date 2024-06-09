package com.goorm.codeAdventure.user.login;


import com.goorm.codeAdventure.user.domain.User;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class LoginController {

    private final LoginService loginService;

    //사용자 등록 엔드포인트
    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody LoginForm loginForm) {
        User user = new User();
        user.setLoginId(loginForm.getLoginId());
        user.setLoginPassword(loginForm.getLoginPassword());

        loginService.join(user);
        return ResponseEntity.ok().build();
    }

    //사용자 로그인 엔드포인트
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginForm loginForm, HttpServletResponse response) {
        User user = loginService.login(loginForm.getLoginId(), loginForm.getLoginPassword());

        if (user != null)
        {
            String welcomeMessage = user.getName() + "님, code adventure에 오신 것을 환영합니다.";

            return ResponseEntity.ok(welcomeMessage);
        }
        else //로그인 실패 시 UNAUTHORIZED(401) 상태 코드 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인에 실패했습니다. 아이디 또는 비밀번호를 확인해주세요.");
    }
}
