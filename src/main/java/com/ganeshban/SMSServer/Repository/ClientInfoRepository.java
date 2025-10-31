package com.ganeshban.smsserver.repository;

import com.ganeshban.smsserver.entity.ClientInfoEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientInfoRepository extends JpaRepository<ClientInfoEntity, String> {
    @Cacheable(cacheNames = "clientInfo")
    Optional<ClientInfoEntity> findByClientCode(String code);

}
