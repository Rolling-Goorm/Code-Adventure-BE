package com.goorm.codeAdventure.user.login;


import com.goorm.codeAdventure.user.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<String> login(@Valid @RequestBody LoginForm loginForm) {
        User user = loginService.login(loginForm.getLoginId(), loginForm.getLoginPassword());
        return ResponseEntity.ok("Welcome " + user.getName());
    }
}
