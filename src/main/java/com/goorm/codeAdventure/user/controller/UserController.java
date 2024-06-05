package com.goorm.codeAdventure.user.controller;

import com.goorm.codeAdventure.user.domain.User;
import com.goorm.codeAdventure.user.userService.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     *
     */
    @GetMapping("/users/new")
    public String createForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "users/createUserForm";
    }

    @PostMapping("/users/new")
    public String create(@Valid UserForm userForm, BindingResult result) {
        if (result.hasErrors()) {
            return "users/createUserForm";
        }

        // userForm을 가지고 입력을 받은 내용을 다시 Entity인 User객체에 생성자로 정보를 담는다.
        User user = new User(
                userForm.getLoginId(),
                userForm.getLoginPassword(),
                userForm.getName(),
                userForm.getNickname(),
                userForm.getPrefferedLanguage(),
                userForm.getBirth(),
                userForm.getEmail(),
                userForm.getPhoneNumber()
        );

        userService.join(user);
        return "users/login"; // 로그인 화면창으로?
    }

}
