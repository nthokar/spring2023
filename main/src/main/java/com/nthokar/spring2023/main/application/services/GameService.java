package com.nthokar.spring2023.main.application.services;

import com.nthokar.spring2023.main.application.MoveDTO;
import com.nthokar.spring2023.main.domain.chess.game.Game;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class GameService implements com.nthokar.spring2023.main.application.interfaces.GameService {

    HashMap<String, Game> games = new HashMap<>();
    Game createNewGame(Game.Builder builder) {
        var game = builder.build();
        return games.put(game.id, game);
    }

    public boolean processMove(MoveDTO moveDTO, String playerId){
        var game = getGame(moveDTO.gameId);
        if (game.whitePlayer.getId().equals(playerId))
            return game.applyMove(moveDTO, game.whitePlayer);
        if (game.blackPlayer.getId().equals(playerId))
            return game.applyMove(moveDTO, game.blackPlayer);
        else
            throw new RuntimeException(String.format("no player with id: %s in game", playerId));
    }
    @Override
    public Game getGame(String id) {
        return games.get(id);
    }

}
