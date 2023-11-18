package com.nthokar.spring2023.main.domain.chess.logic.parsers;

import com.nthokar.spring2023.main.domain.chess.logic.Coordinate;
import com.nthokar.spring2023.main.domain.chess.logic.board.Square;
import com.nthokar.spring2023.main.domain.chess.logic.figures.King;
import com.nthokar.spring2023.main.domain.chess.logic.figures.Knight;
import com.nthokar.spring2023.main.domain.chess.logic.figures.Pawn;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PositionEncodingTest {

    @Test
    void encode() {
        var board = new Square[3][3];
        board[0][0] = new Square(Pawn.getClassic(Color.BLACK), Color.WHITE, new Coordinate(1, 1));
        board[0][1] = new Square(Pawn.getClassic(Color.BLACK), Color.BLACK, new Coordinate(1, 2));
        board[0][2] = new Square(Pawn.getClassic(Color.BLACK), Color.BLACK, new Coordinate(1, 2));
        board[1][0] = new Square(null, Color.BLACK, new Coordinate(2, 1));
        board[1][1] = new Square(null, Color.WHITE, new Coordinate(2, 2));
        board[1][2] = new Square(null, Color.WHITE, new Coordinate(2, 2));
        board[2][0] = new Square(King.getClassic(Color.BLACK), Color.BLACK, new Coordinate(2, 1));
        board[2][1] = new Square(Knight.getClassic(Color.WHITE), Color.WHITE, new Coordinate(2, 2));
        board[2][2] = new Square(Knight.getClassic(Color.WHITE), Color.WHITE, new Coordinate(2, 2));

        var encode = PositionEncoding.encode(board);

        Assert.assertEquals( "3P3.K2n", encode);
    }
}