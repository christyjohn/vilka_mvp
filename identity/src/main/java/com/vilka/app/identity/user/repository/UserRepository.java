package com.vilka.app.identity.user.repository;

import com.vilka.app.identity.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsById(Long id);

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

}
