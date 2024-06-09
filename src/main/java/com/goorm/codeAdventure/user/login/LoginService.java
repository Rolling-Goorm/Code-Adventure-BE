package com.goorm.codeAdventure.user.login;

import com.goorm.codeAdventure.user.domain.User;
import com.goorm.codeAdventure.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@Transactional
@RequiredArgsConstructor
public class LoginService{

    private final UserRepository userRepository;

    //사용자 등록
    public Long join(User user) {

        userRepository.save(user);

        return user.getId(); //사용자 ID 반환
    }

    //사용자 정보 조회
    public User login(String loginId, String loginPassword) {
        User user = userRepository.findOne(loginId, loginPassword);
        if (user == null) {
            throw new IllegalArgumentException("사용자 정보가 존재하지 않습니다.");
        }
        return user;
    }





}
