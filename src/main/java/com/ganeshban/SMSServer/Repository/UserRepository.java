package com.ganeshban.SMSServer.Repository;

import com.ganeshban.SMSServer.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String username);
    Optional<UserEntity> findByClientCode(String clientCode);
}
