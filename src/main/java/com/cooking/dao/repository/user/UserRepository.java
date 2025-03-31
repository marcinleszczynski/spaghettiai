package com.cooking.dao.repository.user;

import com.cooking.dao.model.user.User;
import com.cooking.dao.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends AbstractRepository<User> {
    Optional<User> findByEmail(String email);
}
