package com.foundationProject.BookStore.repository;

import com.foundationProject.BookStore.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String emailOrPhone);

    boolean existsByUsername(String username);
}
