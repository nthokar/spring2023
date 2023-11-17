package com.nthokar.spring2023.main.domain.chess.game;

import com.nthokar.spring2023.main.application.MoveDTO;
import com.nthokar.spring2023.main.domain.chess.logic.board.Board;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Game {
    public final String id;

    /**
     * players in game
     */
    public final Player whitePlayer;
    public final Player blackPlayer;

    /**
     * state of game
     */

    @Getter
    State state = State.PREPARE;
    public enum State {
        PREPARE,
        IN_GAME,
        END
    }

    /**
     * player which move 
     */
    private Player whichTurn;
    private final Board board;
    private final Timer timer;

    /**
     * game builder
     * that's class used for configure game,
     * before its starts.
     */
    public static class Builder {
        public final String id = UUID.randomUUID().toString();
        public Timer timer;
        public Player whitePlayer;
        public Player blackPlayer;

        public Board board;

        public Builder setWhitePlayer(Player whitePlayer){
            this.whitePlayer= whitePlayer;
            return this;
        }

        public Builder setBlackPLayer(Player blackPlayer){
            this.blackPlayer = blackPlayer;
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

        public Game build() {
            if (Objects.isNull(whitePlayer) || Objects.isNull(blackPlayer)){
                log.warn("can't build game without player(s)");
                throw new RuntimeException();
            }
            return new Game(this);
        }
    }
    protected Game(Game.Builder builder) {
        this.board = builder.board;
        this.whitePlayer = builder.whitePlayer;
        this.blackPlayer = builder.blackPlayer;
        this.timer = builder.timer;
        this.id = builder.id;
    }
    /**
     * process given move to game,
     * apply move if it satisfies the rules.
     */
    public boolean processMove(MoveDTO move) {
        if (state != State.IN_GAME) return false;
        log.info(whichTurn == whitePlayer ? "white's turn..." : "black's turn...");
        boolean isValid = board.moveFigureIfLegal(move.toMove())
                && whichTurn.getId().equals(move.userId);
        if (isValid) {
            whichTurn = whichTurn == whitePlayer ? blackPlayer : whitePlayer;
            log.info("move applied");
            return true;
        }
        else {
            log.info("move isn't valid");
            return false;
        }
    }
}
