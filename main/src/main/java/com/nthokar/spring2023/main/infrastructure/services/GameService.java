package com.nthokar.spring2023.main.infrastructure.services;

import com.nthokar.spring2023.main.application.Game;

import java.util.HashMap;

public class GameService implements com.nthokar.spring2023.main.application.interfaces.GameService {

    HashMap<String, Game> games;
    @Override
    public Game createNewGame(Game.Builder builder) {
        var game = builder.build();
        return games.put(game.id, game);
    }

    @Override
    public Game getGame(String id) {
        return games.get(id);
    }
}
