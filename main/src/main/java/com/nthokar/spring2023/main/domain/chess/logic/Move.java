package com.nthokar.spring2023.main.domain.chess.logic;

import com.nthokar.spring2023.main.domain.chess.logic.board.Square;
import com.nthokar.spring2023.main.domain.chess.logic.figures.Figure;
import com.nthokar.spring2023.main.domain.chess.logic.parsers.CoordinateParser;

public class Move {
    /**
     * start square of move
     */
    Square startSquare;
    /**
     * ens square of move
     */
    Square endSquare;

    float spendTime;

    /**
     *
     * @param startSquare start square of move
     * @param endSquare ens square of move
     */
    public Move (Square startSquare, Square endSquare){
        this.startSquare = startSquare;
        this.endSquare = endSquare;
    }
    public Move (String string) {
        try {
            var startCoordinate = CoordinateParser.parseToCoordinate(string.substring(0, 2));
            var endCoordinate = CoordinateParser.parseToCoordinate(string.substring(2));
            this.startSquare = new Square(null, null,startCoordinate);
            this.endSquare = new Square(null, null, endCoordinate);
        }
        catch (Exception e)
        {
                throw new RuntimeException("wrong input");
        }
    }

    /**
     *
     * @param startCoordinate start coordinate of move
     * @param endCoordinate ens coordinate of move
     */
    public Move (Coordinate startCoordinate, Coordinate endCoordinate){
        this.startSquare = new Square(null, null,startCoordinate);
        this.endSquare = new Square(null, null, endCoordinate);
    }
    public Coordinate getStartCoordinate(){
        return startSquare.coordinate;
    }

    public Coordinate getEndCoordinate(){
        return endSquare.coordinate;
    }

    public Figure getStartFigure(){
        return startSquare.getFigure();
    }

    public Figure getEndFigure(){
        return endSquare.getFigure();
    }
}
