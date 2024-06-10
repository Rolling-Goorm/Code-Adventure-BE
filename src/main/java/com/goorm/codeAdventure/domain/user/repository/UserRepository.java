package com.goorm.codeAdventure.domain.user.repository;

import com.goorm.codeAdventure.domain.user.entity.User;
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
    public User findById(Long id) {
        return em.find(User.class, id);
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
