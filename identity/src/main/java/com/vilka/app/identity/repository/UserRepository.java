package com.vilka.app.identity.repository;

import com.vilka.app.identity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsById(Long id);

    boolean existsByEmail(String email);
}
