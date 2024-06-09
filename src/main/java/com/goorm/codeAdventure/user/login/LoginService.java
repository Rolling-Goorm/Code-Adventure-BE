package com.goorm.codeAdventure.user.login;

import com.goorm.codeAdventure.user.domain.User;
import com.goorm.codeAdventure.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    //사용자 등록
    public Long join(User user) {

        userRepository.save(user);

        return user.getId(); //사용자 ID 반환
    }

    public User login(String loginId, String password) {
        User user = userRepository.findOne(loginId, password);
        if (user == null) {
            throw new IllegalArgumentException("Invalid login credentials");
        }
        return user;
    }





}
