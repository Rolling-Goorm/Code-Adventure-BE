package com.goorm.codeAdventure.domain.login.service;

import com.goorm.codeAdventure.domain.user.entity.User;
import com.goorm.codeAdventure.domain.user.repository.LoginRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@Transactional
@RequiredArgsConstructor
public class LoginService{

    private final LoginRepository loginRepository;

    //사용자 등록
    public Long join(User user) {

        loginRepository.save(user);

        return user.getId(); //사용자 ID 반환
    }

    //사용자 정보 조회
    public User login(String loginId, String loginPassword) {
        User user = loginRepository.findOne(loginId, loginPassword);
        if (user == null) {
            throw new IllegalArgumentException("사용자 정보가 존재하지 않습니다.");
        }
        return user;
    }





}