package com.nthokar.spring2023.main.domain.chess.logic.parsers;

import com.nthokar.spring2023.main.domain.chess.logic.board.Square;
import com.nthokar.spring2023.main.domain.chess.logic.figures.Figure;

import java.awt.*;
import java.util.Objects;

public class PositionEncoding {
    public static String encode(Figure figure) {
        if (Objects.isNull(figure)) return ".";
        String shortcut = ((Character) figure.name.toLowerCase().charAt(0)).toString();
        if (Objects.equals(figure.name.toLowerCase(), "knight")) {
            shortcut = "n";
        }
        return figure.color == Color.WHITE ? shortcut : shortcut.toUpperCase();
    }

    public static String encode(Square[][] squares) {
        StringBuilder builder = new StringBuilder();
        for (var i = 0; i < squares.length; i++) {
            for (var j = 0; j < squares.length; j++) {
                var figure = squares[i][j].getFigure();
                var encoded = encode(figure);
                builder.append(encoded);
            }
        }
        return replaceRepeats(builder.toString());
    }

    private static String replaceRepeats(String str) {
        var builder = new StringBuilder();
        int count = 1;
        for (var i = 0; i < str.length(); i++) {
            if ( i + 1 < str.length() && str.charAt(i) == str.charAt(i + 1)) {
                count++;
            }
            else {
                if (count > 1) builder.append(count);
                builder.append(str.charAt(i));
                count = 1;
            }
        }
        return builder.toString();
    }
}
