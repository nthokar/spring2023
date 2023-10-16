package com.nthokar.spring2023.main.domain.chess.logic.rules;


import com.nthokar.spring2023.main.domain.chess.logic.Move;
import com.nthokar.spring2023.main.domain.chess.logic.board.Board;

@FunctionalInterface
public interface Rule {
    boolean apply(Move move, Board board);
}
