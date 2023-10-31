package com.nthokar.spring2023.main.application.services;

import com.nthokar.spring2023.main.application.entities.UserEntity;
import com.nthokar.spring2023.main.application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired  UserRepository repo;

    public Optional<UserEntity> getUser(String userId){
        return repo.findById(userId);
    }
    public Optional<UserEntity> getOpponent(){

    }
}
