package com.nthokar.spring2023.main.domain.chess.logic.parsers;

import com.nthokar.spring2023.main.domain.chess.logic.board.Square;

public class PositionEncoded {
    public PositionEncoded(Square[][] position) {
        var builder = new StringBuilder();
        for (int i = 0; i < position.length; i++){
            builder.append('/');

            builder.append('/');
        }
    }

    String positionEncoded;
}
