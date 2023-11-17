package com.nthokar.spring2023.main.domain.chess.DTO;

import com.nthokar.spring2023.main.domain.chess.logic.Move;
import com.nthokar.spring2023.main.domain.chess.logic.parsers.CoordinateParser;

public class MoveDTO {

    public MoveDTO(Move move) {
        this.move = CoordinateParser.parseToString(move.getStartCoordinate())
                + CoordinateParser.parseToString(move.getEndCoordinate());
    }

    /**
     * move as string, example: e2e4
     */

    public String move;

    /**
     *  time delta between last move and this move timestamp
     */
    public Float timeSpent;
}
