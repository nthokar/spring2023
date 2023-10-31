package com.nthokar.spring2023.main.application.repositories;

import com.nthokar.spring2023.main.application.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserEntity, String> {
}
