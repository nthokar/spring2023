package com.nthokar.spring2023.main.application;

import com.nthokar.spring2023.main.domain.chess.logic.Move;

public class MoveDTO {
    public String gameId;
    public String userId;

    public String move;
    public Float timeSpent;

    public Move toMove(){
        return new Move(move);
    }
}
