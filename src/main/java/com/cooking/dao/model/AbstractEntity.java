package com.cooking.dao.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class AbstractEntity {

    @Id
    private UUID id;

    private Date creationTimestamp;
    private Date modificationTimestamp;

    @PrePersist
    void onCreate() {
        if (id == null) {
            id = UUID.randomUUID();
        }
        creationTimestamp = new Date();
        modificationTimestamp = creationTimestamp;
    }

    @PreUpdate
    void onUpdate() {
        modificationTimestamp = new Date();
    }
}
