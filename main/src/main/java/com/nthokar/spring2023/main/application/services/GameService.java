package com.nthokar.spring2023.main.application.services;

import com.nthokar.spring2023.main.application.entities.UserEntity;
import com.nthokar.spring2023.main.application.WebPlayer;
import com.nthokar.spring2023.main.application.repositories.UserRepository;
import com.nthokar.spring2023.main.domain.chess.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Objects;

@Service
public class GameService implements com.nthokar.spring2023.main.application.interfaces.GameService {
    @Autowired
    UserRepository userRepository;

    HashMap<String, Game> games;
    Game createNewGame(Game.Builder builder) {
        var game = builder.build();
        return games.put(game.id, game);
    }

    @Override
    public Game getGame(String id) {
        return games.get(id);
    }

}
