package com.ganeshban.smsserver.repository;

import com.ganeshban.smsserver.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByUserName(String username);
    Optional<UserEntity> findByUserNameAndPassword(String username, String password);
}

