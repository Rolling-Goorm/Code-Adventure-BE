package com.goorm.codeAdventure.user.repository;

import com.goorm.codeAdventure.domain.user.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User,Long> {
    //User에 대한 db 접근 처리하는 부분 .
}
