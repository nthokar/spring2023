package com.nthokar.spring2023.main.application.services;

import com.nthokar.spring2023.main.application.WebPlayer;
import com.nthokar.spring2023.main.domain.chess.game.Game;
import com.nthokar.spring2023.main.domain.chess.game.TimeStandard;
import com.nthokar.spring2023.main.domain.chess.game.Timer;
import com.nthokar.spring2023.main.domain.chess.logic.board.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class GameBuilderService {
    @Autowired MatchmakingService matchmakingService;
    HashMap<String, Game.Builder> games = new HashMap<>();

    public Game.Builder createClassicBoard(WebPlayer player) {
        var game = new Game.Builder()
                .setBoard(new Board.Builder()
                        .setDefault()
                        .build())
                .setTimer(new Timer(600, 5));
        game.setWhitePlayer(player);
        games.put(game. id, game);
        return game;
    }
    public void setTimer(Game.Builder game, String timeEncoded) {
        game.setTimer(TimeStandard.parse(timeEncoded).toTimer());
    }
    public Game.Builder getGame(String id) {
        return games.get(id);
    }
    public void build(String id){
        var game = getGame(id);
        matchmakingService.addToQueue(game);
        games.remove(id);
    }
}
