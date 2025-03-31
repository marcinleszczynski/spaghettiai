package com.cooking.dao.repository;

import com.cooking.dao.model.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AbstractRepository<T extends AbstractEntity> extends JpaRepository<T, UUID> {
}
