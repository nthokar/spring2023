package com.nthokar.spring2023.main.application.services;

import com.nthokar.spring2023.main.application.WebPlayer;
import com.nthokar.spring2023.main.application.entities.UserEntity;
import com.nthokar.spring2023.main.domain.chess.game.Game;
import com.nthokar.spring2023.main.domain.chess.game.Timer;
import com.nthokar.spring2023.main.domain.chess.logic.board.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class GameBuilderService {
    HashMap<String, Game.Builder> games = new HashMap<>();

    public void findPlayerByElo(Game.Builder builder){

    }

    public Game.Builder createClassicBoard() {
        var game = new Game.Builder()
                .setBoard(new Board.Builder()
                        .setDefault()
                        .build())
                .setTimer(new Timer(600, 5));
        games.put(game. id, game);
        return game;
    }
    public Game.Builder createBuilder(Game.Builder game) {
        games.put(game.id, game);
        return game;
    }
    public void setTimer(Game.Builder game, Integer time, Integer extraTime) {
        game.setTimer(new Timer(time, extraTime));
    }
    public Game.Builder getGame(String id) {
        return games.get(id);
    }
}
