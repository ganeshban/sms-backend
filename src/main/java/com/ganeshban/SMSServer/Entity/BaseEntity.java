package com.ganeshban.SMSServer.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;
    @CreationTimestamp
    @Column(name = "CREATED_AT",updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "STATUS")
    private String status = "ACTIVE";
}
