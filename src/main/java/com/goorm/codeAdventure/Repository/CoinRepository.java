package com.goorm.codeAdventure.Repository;

import com.goorm.codeAdventure.user.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<User,Long> {
    //User에 대한 db 접근 처리 .
}
