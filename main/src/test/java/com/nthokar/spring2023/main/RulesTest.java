package com.nthokar.spring2023.main;

import com.nthokar.spring2023.main.domain.chess.logic.Coordinate;
import com.nthokar.spring2023.main.domain.chess.logic.Move;
import com.nthokar.spring2023.main.domain.chess.logic.board.Board;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;


public class RulesTest {

    @Test
    void pawnMove() {
        var board = new Board.Builder().setDefaultPosition().build();
        var whiteMoveForward = board.isMoveLegal(new Move(new Coordinate(1, 2), new Coordinate(1, 3)));
        var blackMoveForward = board.isMoveLegal(new Move(new Coordinate(1, 7), new Coordinate(1, 6)));
        Assert.isTrue(whiteMoveForward, "white pawn can't move forward");
        Assert.isTrue(blackMoveForward, "black pawn can't move forward");
    }

    @Test
    void pawnJump() {
        var board = new Board.Builder().setDefaultPosition().build();
        var whiteMoveForward = board.isMoveLegal(new Move(new Coordinate(1, 2), new Coordinate(1, 4)));
        var blackMoveForward = board.isMoveLegal(new Move(new Coordinate(1, 7), new Coordinate(1, 5)));
        Assert.isTrue(whiteMoveForward, "white pawn can't jump");
        Assert.isTrue(blackMoveForward, "black pawn can't jump");
    }

    @Test
    void pawnJumpAsSecondMove() {
        var board = new Board.Builder().setDefaultPosition().build();
        board.moveFigureIfLegal(new Move(new Coordinate(1, 2), new Coordinate(1, 3)));
        board.moveFigureIfLegal(new Move(new Coordinate(2, 7), new Coordinate(2, 6)));

        var whiteMoveForward = board.isMoveLegal(new Move(new Coordinate(1, 3), new Coordinate(1, 5)));
        var blackMoveForward = board.isMoveLegal(new Move(new Coordinate(2, 6), new Coordinate(1, 4)));
        Assert.isTrue(!whiteMoveForward, "white pawn jump after moved forward");
        Assert.isTrue(!blackMoveForward, "black pawn jump after moved forward");
    }
}