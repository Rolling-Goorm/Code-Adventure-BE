package com.goorm.codeAdventure.domain.user.service;

import com.goorm.codeAdventure.domain.user.dto.request.LoginForm;
import com.goorm.codeAdventure.domain.user.dto.request.UserForm;
import com.goorm.codeAdventure.domain.user.dto.response.UserResponse;
import com.goorm.codeAdventure.domain.user.entity.SessionConst;
import com.goorm.codeAdventure.domain.user.entity.User;
import com.goorm.codeAdventure.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public void join(UserForm userForm) {

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

    public void join(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);
    }

    // 같은 별명의 회원 검증 --> RuntimeException 으로 바꿔야함
    private void validateDuplicateUser(User user) {
        List<User> findUsers = userRepository.findByNickName(user.getNickname());

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


    public UserResponse findUser(Long userId) {
        return new UserResponse(findOne(userId));
    }

    /**
     * 회원 정보 업데이트
     */
    public void updateUser(Long userId, UserForm updateUserForm) {
        User findUser = findOne(userId);
        findUser.updateUser(updateUserForm);
    }

    /**
     * 회원 로그인
     * @param loginForm
     * @param response
     * @return 로그인 성공 | 실패
     */
    public ResponseEntity<String> login(LoginForm loginForm, HttpServletRequest request, HttpServletResponse response) {
        User user = login(loginForm.getLoginId(), loginForm.getLoginPassword());

        if (user != null)
        {
            // 별명으로 회원가입 검증을 하기도해서 이름대신 닉네임으로 하는게 어떨까요?
            String welcomeMessage = user.getNickname() + "님, code adventure에 오신 것을 환영합니다.";

            //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
            HttpSession session = request.getSession();

            //세션에 로그인 회원 정보 보관
            session.setAttribute(SessionConst.LOGIN_USER, user);

            return ResponseEntity.ok(welcomeMessage);
        }
        else // 로그인 실패 시 UNAUTHORIZED(401) 상태 코드 반환 --> 상태가 500으로 출력됩니당...
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인에 실패했습니다. 아이디 또는 비밀번호를 확인해주세요.");
    }

    public User login(String loginId, String loginPassword) {
        User user = userRepository.validateUser(loginId, loginPassword);
        if (user == null) {
            throw new IllegalArgumentException("사용자 정보가 존재하지 않습니다.");
        }
        return user;
    }

}
