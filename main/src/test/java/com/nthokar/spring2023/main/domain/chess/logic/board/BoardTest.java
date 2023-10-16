package com.nthokar.spring2023.main.domain.chess.logic.board;

import com.nthokar.spring2023.main.domain.chess.logic.Coordinate;
import com.nthokar.spring2023.main.domain.chess.logic.Move;
import com.nthokar.spring2023.main.domain.chess.logic.figures.King;
import com.nthokar.spring2023.main.domain.chess.logic.figures.Queen;
import com.nthokar.spring2023.main.domain.chess.logic.figures.Rook;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.awt.*;
import java.util.Objects;


public class BoardTest {
    @Test
    void getSquare() {
        var board = new Board.Builder().setDefault().build();
        var isRook = board.getSquare(new Coordinate(1,1)).getFigure() instanceof Rook;
        try {
            board.getSquare(new Coordinate(100,100));
        }
        catch (RuntimeException e) {
            var isException = Objects.equals(e.getMessage(), "square out of board");
            Assert.isTrue(isException, "wrong exception");
        }

        Assert.isTrue(isRook, "its not rook");
    }

    @Test
    void getFiguresByColor() {
        var board = new Board.Builder().setFigure(King.getClassic(Color.BLACK), new Coordinate(1,1)).build();
        var kingSquare = board.getSquare(new Coordinate(1,1));


        var blackFigures = board.getFigureSquaresByColor(Color.BLACK);
        var result = blackFigures.contains(kingSquare) && blackFigures.size() == 1;

        Assert.isTrue(result, "king not in black figures");
    }

    @Test
    void isCheckAfterMove() {
        var board = new Board.Builder()
                .setFigure(King.getClassic(Color.WHITE), new Coordinate(1,1))
                .setFigure(Queen.getClassic(Color.WHITE), new Coordinate(1,2))
                .setFigure(Queen.getClassic(Color.BLACK), new Coordinate(1,5))
                .setDefaultRules()
                .build();
        var invalidQueenMove = new Move(new Coordinate(1,2), new Coordinate(2,2));
        var isLegal = board.isMoveLegal(invalidQueenMove);

        if (isLegal){
            board.moveFigureIfLegal(invalidQueenMove);
            board.print();
        }

        Assert.isTrue(!isLegal, "invalid move! you move give check to your king");
    }

    @Test
    void isCheckMate() {
        var board = new Board.Builder()
                .setDefaultRules()
                .setFigure(Queen.getClassic(Color.BLACK), new Coordinate(1,1))
                .setFigure(Queen.getClassic(Color.BLACK), new Coordinate(2,1))
                .setFigure(King.getClassic(Color.BLACK), new Coordinate(8,8))
                .setFigure(King.getClassic(Color.WHITE), new Coordinate(1,8))
                .build();
        board.print();
        var start = System.currentTimeMillis();
        board.isCheckMate(Color.WHITE);
        System.out.println(System.currentTimeMillis() - start);
        Assert.isTrue(board.state == Board.State.CHECK_MATE, "isnt mate");
    }
}