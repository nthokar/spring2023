package com.nthokar.spring2023.main.domain.chess.logic.board;

import com.nthokar.spring2023.main.domain.chess.logic.Move;

import java.util.List;
import java.util.Stack;

public class MovesHistory {
    private final Stack<Move> moveStack = new Stack<>();

    public List<Move> getHistory(){
        return moveStack.stream().toList();
    }

    public void addMove(Move move){
        moveStack.add(move);
    }
}
