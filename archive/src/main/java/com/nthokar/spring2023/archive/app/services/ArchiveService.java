package com.nthokar.spring2023.archive.app.services;

import com.nthokar.spring2023.archive.domain.Game;

import java.util.Optional;

public interface ArchiveService {
    Optional<Game> getGame(String gameId);
    void saveGame(Game game);
}
