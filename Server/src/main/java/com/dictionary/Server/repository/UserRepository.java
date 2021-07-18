package com.dictionary.Server.repository;

import com.dictionary.Server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
     User findUserByEmail(String email);
     Optional<User> findUserByUsername(String username);
}