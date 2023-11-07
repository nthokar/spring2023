package com.nthokar.spring2023.userauth.application;

import com.nthokar.spring2023.userauth.application.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByName(String email);
}