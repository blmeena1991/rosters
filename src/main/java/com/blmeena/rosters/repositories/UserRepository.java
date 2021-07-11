package com.blmeena.rosters.repositories;

import com.blmeena.rosters.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUserNameOrEmail(String username, String email);

    Optional<User> findByUserName(String username);
}