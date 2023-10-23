package com.nthokar.spring2023.main.application.services;

import com.nthokar.spring2023.main.application.entities.UserEntity;
import com.nthokar.spring2023.main.application.WebPlayer;
import com.nthokar.spring2023.main.application.repositories.UserRepository;
import com.nthokar.spring2023.main.domain.chess.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class GameService implements com.nthokar.spring2023.main.application.interfaces.GameService {
    @Autowired
    UserRepository userRepository;

    HashMap<String, Game> games;
    HashMap<String, Game.Builder> games1;
    @Override
    public Game createNewGame(Game.Builder builder) {
        var game = builder.build();
        return games.put(game.id, game);
    }

    @Override
    public Game getGame(String id) {
        return games.get(id);
    }

    public void connectToGame(String gameId, UserEntity userEntity) {
        var game = games1.get(gameId);
        if (Objects.nonNull(game.blackPlayer) && Objects.nonNull(game.whitePlayer)){
            //log
            throw new RuntimeException();
        }
        var player = new WebPlayer(userEntity);
        if (Objects.isNull(game.whitePlayer))
            game.setWhitePlayer(player);
        else
            game.setPlayer2(player);
    }
}
