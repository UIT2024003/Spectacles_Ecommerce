package com.spectacles.spectacles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spectacles.spectacles.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}