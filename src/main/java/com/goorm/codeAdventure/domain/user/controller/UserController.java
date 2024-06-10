package com.goorm.codeAdventure.domain.user.controller;

import com.goorm.codeAdventure.domain.user.dto.request.UserForm;
import com.goorm.codeAdventure.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/new")
    public ResponseEntity<Void> create(@Valid @RequestBody UserForm userForm) {

        userService.login(userForm);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/users/{userId}}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId, @RequestBody UserForm updateUserForm) {

        userService.updateUser(userId, updateUserForm);

        return ResponseEntity.noContent().build();
    }

}
