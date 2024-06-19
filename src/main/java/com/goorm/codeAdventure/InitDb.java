package com.goorm.codeAdventure;


import com.goorm.codeAdventure.domain.game.entity.Category;
import com.goorm.codeAdventure.domain.game.entity.ProgrammingLanguage;
import com.goorm.codeAdventure.domain.game.entity.Progress;
import com.goorm.codeAdventure.domain.game.entity.Stage;
import com.goorm.codeAdventure.domain.item.entity.Item;
import com.goorm.codeAdventure.domain.problem.entity.AttemptResult;
import com.goorm.codeAdventure.domain.problem.entity.Problem;
import com.goorm.codeAdventure.domain.user.entity.User;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        this.initService.createUsers();
        this.initService.createGame();
        this.initService.createItems();
    }

    @Component
    @RequiredArgsConstructor
    static class InitService {
        private static final Logger log = LoggerFactory.getLogger(InitService.class);
        private final EntityManager em;

        @Transactional
        public void createUsers() {
            User user1 = new User(
                    "admin",
                    "admin",
                    "name1",
                    "nickname1",
                    "JAVASCRIPT",
                    LocalDate.now(),
                    "email1",
                    "phoneNumber1"
            );

            User user2 = new User(
                    "admin2",
                    "admin2",
                    "name2",
                    "nickname2",
                    "JAVA",
                    LocalDate.now(),
                    "email2",
                    "phoneNumber2"
            );

            em.persist(user1);
            em.persist(user2);
            em.flush();
        }

        @Transactional
        public void createGame() {
            Stage stage1 = new Stage(
                    ProgrammingLanguage.JAVA,
                    Category.IO,
                    "Easy"
            );
            Stage stage2 = new Stage(
                    ProgrammingLanguage.JAVA,
                    Category.IO,
                    "Easy"
            );
            Stage stage3 = new Stage(
                    ProgrammingLanguage.JAVA,
                    Category.IO,
                    "Easy"
            );
            em.persist(stage1);
            em.persist(stage2);
            em.persist(stage3);

            Problem problem1 = new Problem(
                    "Hello World",
                    "Hello World!를 출력하시오.",
                    "없음",
                    "Hello World!를 출력하시오.",
                    stage1
            );
            Problem problem2 = new Problem(
                    "A+B",
                    "두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.",
                    "첫째 줄에 A와 B가 주어진다. (0 < A, B < 10)",
                    "첫째 줄에 A+B를 출력한다.",
                    stage2
            );
            Problem problem3 = new Problem(
                    "A+B",
                    "두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.",
                    "첫째 줄에 A와 B가 주어진다. (0 < A, B < 10)",
                    "첫째 줄에 A+B를 출력한다.",
                    stage3
            );
            em.persist(problem1);
            em.persist(problem2);
            em.persist(problem3);

            Problem.IoExample ioExample1 = new Problem.IoExample(problem1, "", "Hello World!");
            Problem.IoExample ioExample2 = new Problem.IoExample(problem2, "1 2", "3");
            Problem.IoExample ioExample3 = new Problem.IoExample(problem2, "1 2", "3");
            em.persist(ioExample1);
            em.persist(ioExample2);
            em.persist(ioExample3);

            List<User> users = em.createQuery("select u from User u", User.class)
                    .getResultList();

            Progress progress1 = new Progress(users.get(0), stage1, AttemptResult.SUCCESS);
            Progress progress2 = new Progress(users.get(0), stage2, AttemptResult.SUCCESS);
            em.persist(progress1);
            em.persist(progress2);

            em.flush();
        }

        @Transactional
        public void createItems() {
            Item item1 = new Item(
                    "황금올리브 치킨 + 콜라 1.25L",
                    800,
                    1
            );
            Item item2 = new Item(
                    "시그니엘 프리미어 더블 시티뷰 숙박권",
                    80000,
                    10
            );
            em.persist(item1);
            em.persist(item2);

            em.flush();
        }
    }
}