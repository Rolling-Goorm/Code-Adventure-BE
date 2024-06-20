package com.goorm.codeAdventure.domain.user.controller;

import com.goorm.codeAdventure.domain.user.dto.request.LoginForm;
import com.goorm.codeAdventure.domain.user.dto.request.UserForm;
import com.goorm.codeAdventure.domain.user.dto.response.UserResponse;
import com.goorm.codeAdventure.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "회원가입 API", description = "새로운 사용자를 생성합니다.")
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
    @Operation(summary = "회원 정보 조회 API", description = "특정 사용자의 정보를 조회합니다.")
    public UserResponse findUser(@PathVariable Long userId) {
        return userService.findUser(userId);
    }

    /**
     * 회원정보 수정
     * @param userId
     * @param updateUserForm
     */
    @PatchMapping("/{userId}")
    @Operation(summary = "회원 정보 수정 API", description = "특정 사용자의 정보를 수정합니다.")
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
    @Operation(summary = "로그인 API", description = "사용자가 로그인합니다.")
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginForm loginForm, HttpServletRequest request, HttpServletResponse response) {
        return userService.login(loginForm, request, response);
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃 API", description = "사용자가 로그아웃합니다.")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        //userService.expireCookie(response, "memberId");

        //세션 무효화
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return ResponseEntity.noContent().build();
    }

}
