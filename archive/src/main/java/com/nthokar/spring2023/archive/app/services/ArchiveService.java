package com.nthokar.spring2023.archive.app.services;

import com.nthokar.spring2023.archive.app.entities.Game;
import com.nthokar.spring2023.archive.app.repos.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArchiveService {
    @Autowired
    GameRepo gameRepo;
    public Optional<Game> GetGame(Long gameId){
        return gameRepo.findById(gameId);
    }
}
