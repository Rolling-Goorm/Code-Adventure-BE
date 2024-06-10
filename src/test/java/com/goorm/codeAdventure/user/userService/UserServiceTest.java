package com.goorm.codeAdventure.user.userService;

import com.goorm.codeAdventure.user.domain.User;
import com.goorm.codeAdventure.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        User user = new User("alstjq");

        // when
        Long savedId = userService.join(user);

        // then
        em.flush();
        assertEquals(user, userRepository.findOne(savedId));
    }

    @Test
    public void 중복_이름_예외() throws Exception {
        // given
        User uesr1 = new User("alstjq");

        User uesr2 = new User("alstjq");

        // when
        userService.join(uesr1);

        // then
        assertThrows(IllegalStateException.class, () -> userService.join(uesr2));
    }

}