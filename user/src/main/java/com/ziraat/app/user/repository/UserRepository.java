package com.ziraat.app.user.repository;

import com.ziraat.app.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByIdentityNumber(String identityNumber);
    Boolean existsByIdentityNumber(String identityNumber);
}
