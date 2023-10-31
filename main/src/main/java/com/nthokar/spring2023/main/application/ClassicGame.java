package com.nthokar.spring2023.main.application;

import com.nthokar.spring2023.main.domain.chess.game.Game;

public class ClassicGame extends Game {

    protected ClassicGame(Builder builder) {
        super(builder);
    }

    public static class Builder extends Game.Builder{
        @Override
        public Game build() {
            return new ClassicGame(this);
        }
    }
}
