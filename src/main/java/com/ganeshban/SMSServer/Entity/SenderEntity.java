package com.ganeshban.smsserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "SENDERS")
public class SenderEntity extends BaseEntity {
    @Column(name = "PHONE", unique = true)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "CLIENT_CODE", referencedColumnName = "CLIENT_CODE")
    @JsonIgnore
    private ClientInfoEntity clientCode;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
