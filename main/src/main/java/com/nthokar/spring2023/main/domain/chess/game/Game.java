package com.nthokar.spring2023.main.domain.chess.game;

import com.nthokar.spring2023.main.domain.chess.logic.Move;

public interface Game {


    void start();
    void applyMove(Move move);
}
