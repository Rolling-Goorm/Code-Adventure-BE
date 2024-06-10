package com.goorm.codeAdventure.user.repository;

/*
    UserRepository는 민섭님의 코드를 가져와서, 조금 수정했습니다.
*/
import com.goorm.codeAdventure.user.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoginRepository {

    @PersistenceContext
    EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    // 회원 id로 조회
    // [수정] 회원 id로 조회 -> 회원id와 회원pw로 조회
    // [수정2] JPQL 사용하여 사용자ID와 비밀번호를 동시에 검증하도록 수정
    public User findOne(String loginId, String loginPassword) {
        List<User> users = em.createQuery("select u from User u where u.loginId = :loginId and u.loginPassword = :loginPassword", User.class)
                .setParameter("loginId", loginId)
                .setParameter("loginPassword", loginPassword)
                .getResultList();
        return users.isEmpty() ? null : users.get(0);
    }
    public List<User> findAll() {
        return em.createQuery("select m from User m", User.class)
                .getResultList();
    }

    // 회원 별명으로 조회
    public List<User> findByName(String nickname) {
        return em.createQuery("select m from User m where m.nickname = :nickname", User.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }


}
