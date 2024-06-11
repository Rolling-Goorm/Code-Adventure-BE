package com.goorm.codeAdventure.domain.user.controller;

import com.goorm.codeAdventure.domain.user.dto.request.LoginForm;
import com.goorm.codeAdventure.domain.user.dto.request.UserForm;
import com.goorm.codeAdventure.domain.user.dto.response.UserResponse;
import com.goorm.codeAdventure.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     * @param userForm
     */
    @PostMapping("/new")
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserForm userForm) {

        userService.join(userForm);

        return ResponseEntity.ok().build();
    }

    /**
     * 회원정보 조회
     * @param userId
     * @return UserResponse
     */
    @GetMapping("/{userId}")
    public UserResponse findUser(@PathVariable Long userId) {
        return userService.findUser(userId);
    }

    /**
     * 회원정보 수정
     * @param userId
     * @param updateUserForm
     */
    @PatchMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId, @RequestBody UserForm updateUserForm) {

        userService.updateUser(userId, updateUserForm);

        return ResponseEntity.noContent().build();
    }

    /**
     * 사용자 로그인 엔드포인트
     * @param loginForm
     * @param response
     */
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginForm loginForm, HttpServletResponse response, HttpServletRequest request) {
        return userService.login(loginForm, response, request);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response, HttpServletRequest request) {
        //userService.expireCookie(response, "memberId");

        //세션 무효화
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return ResponseEntity.noContent().build();
    }

}
