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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
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
        validateDuplicateUser(user.getNickname());
        userRepository.save(user);
    }

    // 같은 별명의 회원 검증 --> RuntimeException 으로 바꿔야함
    private void validateDuplicateUser(String nickName) {
        List<User> findUsers = userRepository.findByNickName(nickName);

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
        validateDuplicateUser(updateUserForm.getNickname());
        User findUser = findOne(userId);
        findUser.updateUser(updateUserForm);
    }

    /**
     * 회원 로그인
     *
     * @param loginForm
     * @return 로그인 성공 | 실패
     */
    public ResponseEntity<UserResponse> login(LoginForm loginForm, HttpServletRequest request) {
        User user = login(loginForm.getLoginId(), loginForm.getLoginPassword());


        if (user == null) {
            throw new IllegalStateException("로그인에 실패했습니다. 아이디 또는 비밀번호를 확인해주세요.");
        }

        UserResponse userResponse = new UserResponse(user);
        // 별명으로 회원가입 검증을 하기도해서 이름대신 닉네임으로 하는게 어떨까요?
        String welcomeMessage = user.getNickname() + "님, code adventure에 오신 것을 환영합니다.";

        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession();

        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_USER, userResponse);
        log.info("New Session ID: " + session.getId() + " for user: " + user.getLoginId());

        return ResponseEntity.ok(userResponse);
    }

    private ResponseEntity<String> checkIsAlreadyLoggedIn(HttpServletRequest request) {
        // 현재 세션에서 로그인 사용자 정보 확인
        HttpSession session = request.getSession(false);
        if (session != null) {
            log.info("Existing Session ID: " + session.getId());
            if (session.getAttribute(SessionConst.LOGIN_USER) != null) {
                User alreadyLoggedInUser = (User) session.getAttribute(SessionConst.LOGIN_USER);
                log.info("Already logged in user: " + alreadyLoggedInUser.getLoginId());
                return ResponseEntity.badRequest().body(alreadyLoggedInUser.getLoginId() + "님은 이미 로그인 되어 있습니다.");
            }
        }
        log.info("No existing session found. Creating a new session.");
        return null;
    }

    public User login(String loginId, String loginPassword) {
        User user = userRepository.validateUser(loginId, loginPassword);
        if (user == null) {
            throw new IllegalArgumentException("사용자 정보가 존재하지 않습니다.");
        }
        return user;
    }

}
