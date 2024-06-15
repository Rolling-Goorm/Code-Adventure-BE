package com.goorm.codeAdventure.user.userService;

import com.goorm.codeAdventure.domain.game.entity.ProgrammingLanguage;
import com.goorm.codeAdventure.domain.problem.entity.ProgrammingLanguage;
import com.goorm.codeAdventure.domain.user.entity.User;
import com.goorm.codeAdventure.domain.user.repository.UserRepository;
import com.goorm.codeAdventure.domain.user.service.UserService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        // given
        User user = new User(
                "loginId1",
                "loginPassword1",
                "name1",
                "nickname1",
                ProgrammingLanguage.JAVA.getName(),
                LocalDate.now(),
                "email1",
                "phoneNumber1"
        );

        // when
        userService.join(user);

        // then
        assertEquals(user, userRepository.findById(1L));
    }

    @Test
    public void 중복_닉네임_예외() throws Exception {
        // given
        User user1 = new User(
                "loginId1",
                "loginPassword1",
                "name1",
                "nickname1",
                ProgrammingLanguage.JAVA.getName(),
                LocalDate.now(),
                "email1",
                "phoneNumber1"
        );

        User user2 = new User(
                "loginId2",
                "loginPassword2",
                "name2",
                "nickname1",
                ProgrammingLanguage.JAVASCRIPT.getName(),
                LocalDate.now(),
                "email2",
                "phoneNumber2"
        );

        // when
        userService.join(user1);

        // then
        assertThrows(IllegalStateException.class, () -> userService.join(user2));
    }

}