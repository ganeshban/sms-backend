package com.ganeshban.SMSServer.Repository;

import com.ganeshban.SMSServer.Entity.ClientInfo;
import com.ganeshban.SMSServer.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByUserName(String username);
    Optional<UserEntity> findByUserNameAndPassword(String username, String password);
}

