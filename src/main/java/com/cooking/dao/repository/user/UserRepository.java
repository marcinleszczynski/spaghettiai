package com.cooking.dao.repository.user;

import com.cooking.dao.model.user.User;
import com.cooking.dao.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends AbstractRepository<User> {
    Optional<User> findByEmailAndActivatedTrue(String email);
    Optional<User> findByActivationCode(UUID activationCode);
}
