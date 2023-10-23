package com.nthokar.spring2023.main.application.services;

import com.nthokar.spring2023.main.domain.chess.game.Game;
import com.nthokar.spring2023.main.domain.chess.game.Timer;
import com.nthokar.spring2023.main.domain.chess.logic.board.Board;
import org.springframework.stereotype.Service;

@Service
public class GameBuilderService {
    public void findPlayerByElo(Game.Builder builder){

    }

    public void invitePlayer(Game.Builder builder, String playerId){

    }

    public Game.Builder createClassicGame() {
        return new Game.Builder()
                .setBoard(new Board.Builder()
                        .setDefault()
                        .build())
                .setTimer(new Timer(600, 5));
    }
}
