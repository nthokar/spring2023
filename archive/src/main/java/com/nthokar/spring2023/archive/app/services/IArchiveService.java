package com.nthokar.spring2023.archive.app.services;

import com.nthokar.spring2023.archive.app.entities.Game;

import java.util.Optional;

public interface IArchiveService {
    Optional<Game> get(String gameId);
    void save(Game game);
}
