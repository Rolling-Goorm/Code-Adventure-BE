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
public class UserRepository {

    @PersistenceContext
    EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    // 회원 id로 조회
    // [수정] 회원 id로 조회 -> 회원id와 회원pw로 조회
    public User findOne(String Loginid, String loginPassword) {
        return em.find(User.class, Loginid);
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
