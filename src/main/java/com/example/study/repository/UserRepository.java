package com.example.study.repository;

import com.example.study.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Boolean existsByUserName(String username);

    UserEntity findByUserName(String username);
}
