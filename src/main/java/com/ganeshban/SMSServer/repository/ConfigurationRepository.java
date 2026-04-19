package com.ganeshban.smsserver.repository;

import com.ganeshban.smsserver.entity.ConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConfigurationRepository extends JpaRepository<ConfigurationEntity, String> {

    Optional<ConfigurationEntity> findByClientCodeAndSenderDevice(String clientCode, String senderDevice);

    List<ConfigurationEntity> findByClientCode(String code);
}
