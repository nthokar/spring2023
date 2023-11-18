package com.nthokar.spring2023.main.domain.chess.logic.parsers;

import com.nthokar.spring2023.main.domain.chess.logic.Coordinate;

public class CoordinateParser {
    public static String parseToString(Coordinate coordinate) {
        Character x = (char) ((int)'a' + coordinate.x() - 1);
        Character y = (char) ((int)'0' + coordinate.y());
        return  x.toString() + y.toString();
    }
    public static Coordinate parseToCoordinate(String str) throws IllegalAccessException {
        if (str.length() != 2)
            throw new IllegalAccessException();
        return new Coordinate((int)str.charAt(0) - (int)'a' + 1, Character.getNumericValue(str.charAt(1)));
    }
}
