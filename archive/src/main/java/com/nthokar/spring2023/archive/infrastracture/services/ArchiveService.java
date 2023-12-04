package com.nthokar.spring2023.archive.infrastracture.services;

import com.nthokar.spring2023.archive.app.entities.Game;
import com.nthokar.spring2023.archive.app.repos.GameRepo;
import com.nthokar.spring2023.archive.app.services.IArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArchiveService implements IArchiveService {
    @Autowired
    GameRepo gameRepo;
    public Optional<Game> getGame(String gameId){
        return gameRepo.findById(gameId);
    }

    @Override
    public void saveGame(Game game) {
        gameRepo.save(game);
    }
}
