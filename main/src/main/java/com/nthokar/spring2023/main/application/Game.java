package com.nthokar.spring2023.main.application;

import com.nthokar.spring2023.main.domain.chess.game.Player;
import com.nthokar.spring2023.main.domain.chess.logic.Move;
import com.nthokar.spring2023.main.domain.chess.logic.board.Board;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import java.awt.*;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Game implements com.nthokar.spring2023.main.domain.chess.game.Game {

    public final String id;

    public final Player playerWhite;
    public final Player playerBlack;

    @Getter
    State state = State.PREPARE;
    public enum State {
        PREPARE,
        IN_GAME,
        END
    }
    private Player whichTurn;
    private final Board board;
    private final Timer timer;

    public static class Builder {

        public Timer timer;
        public Player player1;
        public Player player2;

        public Board board;

        public Builder setPlayer1(Player player1){
            this.player1 = player1;
            return this;
        }

        public Builder setPlayer2(Player player2){
            this.player2 = player2;
            return this;
        }
        public Builder setBoard(Board board){
            this.board = board;
            return this;
        }
        public Builder setTimer(Timer timer){
            this.timer = timer;
            return this;
        }

        public Game build(){
            return new Game(UUID.randomUUID().toString(), player1, player2, board, timer);
        }
    }

    @Override
    public void start() {
        state = State.IN_GAME;
        log.info("Game has been started");
        while (board.getState() == Board.State.IN_GAME) {
            applyMove(whichTurn.getMove());
        }
        state = State.END;
        log.info("Game has been ended");
    }
    @Override
    public void applyMove(Move move) {
        timer.measureTimeForPlayer(() -> {
                    boolean isValid = false;
                    while (!isValid) {
                        isValid = board.moveFigureIfLegal(whichTurn.getMove());
                    }
                },
                whichTurn == playerWhite ? Color.WHITE : Color.BLACK);
        whichTurn = whichTurn == playerWhite ? playerBlack : playerWhite;
        log.info("move applied");
    }
    public void pause(){
        throw new RuntimeException();
    }
    public void surrender(){
        throw new RuntimeException();
    }
    public void draw(){
        throw new RuntimeException();
    }
}
