package com.example.pvbackend.repository;

import com.example.pvbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    // ✅ Count pending users (for dashboard badge)
    long countByEnabledFalse();

    // ✅ Get only users who are not enabled
    List<User> findByEnabledFalse();

    // ✅ Get all admins
    List<User> findByRole(String role);
}