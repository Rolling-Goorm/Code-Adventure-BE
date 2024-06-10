package com.goorm.codeAdventure.domain.user.service;

import com.goorm.codeAdventure.domain.user.entity.User;
import com.goorm.codeAdventure.domain.user.dto.request.UserForm;
import com.goorm.codeAdventure.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원 가입
     */
    public Long join(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    public void login(UserForm userForm) {

        // userForm을 가지고 입력을 받은 내용을 다시 Entity인 User객체에 생성자로 정보를 담는다.
        User user = new User(
                userForm.getLoginId(),
                userForm.getLoginPassword(),
                userForm.getName(),
                userForm.getNickname(),
                userForm.getPreferredLanguage(),
                userForm.getBirth(),
                userForm.getEmail(),
                userForm.getPhoneNumber()
        );

        join(user);
    }

    // 같은 별명의 회원 검증
    private void validateDuplicateUser(User user) {
        List<User> findUsers = userRepository.findByName(user.getNickname());

        if (!findUsers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 이름입니다.");
        }
    }

    /**
     * 전체 회원 조회
     */
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User findOne(Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * 회원 정보 업데이트
     */
    public void updateUser(Long userId, UserForm updateUserForm) {
        User findUser = findOne(userId);
        findUser.updateUser(updateUserForm);
    }
}
