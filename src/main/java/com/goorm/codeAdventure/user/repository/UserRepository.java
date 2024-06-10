package com.goorm.codeAdventure.user.repository;

import com.goorm.codeAdventure.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
