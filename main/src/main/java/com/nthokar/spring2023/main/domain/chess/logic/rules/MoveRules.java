package com.nthokar.spring2023.main.domain.chess.logic.rules;

import com.nthokar.spring2023.main.domain.chess.logic.Castle;
import com.nthokar.spring2023.main.domain.chess.logic.figures.Figure;
import com.nthokar.spring2023.main.domain.chess.logic.Coordinate;

import java.awt.*;
import java.util.Objects;

public class MoveRules {
    /**
     * Правило которое не позволяет движение сквозь другие фигуры
     *
     *  Использование этого правила коректно для расчета пути фигур движушихся строго по горизонтали или вертикали
     *
     *  Возможен вариант, когда вектор будет с углом не кратным 45 градусам,
     *  тогда из-за того что предел задан точкой а не областью цикл будет иметь бесконечное
     *  колличество итераций.
     */
    public static Rule throughAttack = (move, board) -> {
        var dx = move.getEndCoordinate().x() - move.getStartCoordinate().x();
        var dy = move.getEndCoordinate().y() - move.getStartCoordinate().y();

        var ddx = dx == 0 ? 0 : -(dx / Math.abs(dx));
        var ddy = dy == 0 ? 0 : -(dy / Math.abs(dy));

        while (dx != 0 && dy != 0){
            dx += ddx;
            dy += ddy;
            var currentSquare = board.getSquare(new Coordinate(
                    move.getEndCoordinate().x() - dx,
                    move.getEndCoordinate().y() - dy));
            if (currentSquare.getFigure() != null)  {
                if (dx == 0 && dy == 0)
                    return true;
                return false;
            }
        }
        return true;
    };

    /**
     * Правило движения ладии
     */
    public static Rule rookMoveTemplate = (move, board) -> {

        var dx = move.getStartCoordinate().x() - move.getEndCoordinate().x();
        var dy = move.getStartCoordinate().y() - move.getEndCoordinate().y();
        return (dx == 0 || dy == 0) && throughAttack.apply(move, board);
    };

    /**
     * bishop move rule
     */
    public static Rule bishopMoveTemplate = (move, board) -> {

        int dx = Math.abs(move.getStartCoordinate().x() - move.getEndCoordinate().x());
        int dy = Math.abs(move.getStartCoordinate().y() - move.getEndCoordinate().y());

        return (dx == dy) && throughAttack.apply(move, board);
    };
    /**
     * knight move rule
     */
    public static Rule knightMoveTemplate = (move, desk) -> {
        var dx = Math.abs(move.getStartCoordinate().x() - move.getEndCoordinate().x());
        var dy = Math.abs(move.getStartCoordinate().y() - move.getEndCoordinate().y());
        return (dx == 2 & dy == 1) || (dx == 1 & dy == 2);
    };

    /**
     * queen move rule
     */
    public static Rule queenMoveTemplate = (move, desk) -> {
        return rookMoveTemplate.apply(move, desk) || bishopMoveTemplate.apply(move, desk);
    };

    /**
     * king move rule
     */
    public static Rule kingMoveTemplate = (move, desk) -> {
        var dx = Math.abs(move.getStartCoordinate().x() - move.getEndCoordinate().x());
        var dy = Math.abs(move.getStartCoordinate().y() - move.getEndCoordinate().y());
        return (dx <= 1 & dy <= 1);
    };

    /**
     * black pawn capture rule
     */
    public static Rule blackPawnCapturingTemplate = (move, desk) -> {
        Figure capturedFigure = move.getEndFigure();
        if (Objects.isNull(capturedFigure) || capturedFigure.color != Color.black) return false;
        var dx = move.getStartCoordinate().x() - move.getEndCoordinate().x();
        return (dx == 1 || dx == -1);
    };

    /**
     * white pawn capture rule
     */
    public static Rule whitePawnCapturingTemplate = (move, desk) -> {
        Figure capturedFigure = move.getEndFigure();
        if (Objects.isNull(capturedFigure) || capturedFigure.color != Color.white) return false;
        var dx = move.getStartCoordinate().x() - move.getEndCoordinate().x();
        return (dx == 1 || dx == -1);
    };
    public static Rule blackPawnJumpTemplate = (move, desk) -> {
        return move.getEndCoordinate().y() - move.getStartCoordinate().y() == -2 && !move.getStartFigure().isMoved;
    };

    public static Rule whitePawnJumpTemplate = (move, desk) -> {
        return move.getEndCoordinate().y() - move.getStartCoordinate().y() == 2 && !move.getStartFigure().isMoved;
    };

    public static Rule whitePawnMoveTemplate = (move, desk) -> {
        return move.getEndCoordinate().y() - move.getStartCoordinate().y() == 1;
    };

    public static Rule blackPawnMoveTemplate = (move, desk) -> {
        return move.getEndCoordinate().y() - move.getStartCoordinate().y() == -1;
    };

    public static Rule friendlyFire = (move, desk) -> {
        if (Objects.isNull(desk.getSquare(move.getEndCoordinate()).getFigure())) return true;
        return desk.getSquare(move.getStartCoordinate()).getFigure().color != desk.getSquare(move.getEndCoordinate()).getFigure().color;
    };

    public static Rule checkWarning = (move, board) -> {
        return !board.isCheckAfterMove(move);
    };
    public static Rule nullMove = (move, board) -> {
        var dx = move.getEndCoordinate().x() - move.getStartCoordinate().x();
        var dy = move.getEndCoordinate().y() - move.getStartCoordinate().y();
        return !(dx == 0 && dy == 0);
    };

    public static Rule shortCastle = (move, board) -> {
        if (!(move instanceof Castle)) return false;
        var dx = move.getEndCoordinate().x() - move.getStartCoordinate().x();
        var dy = move.getEndCoordinate().y() - move.getStartCoordinate().y();
        if (dx != 2 && dy != 2) return false;
        if (move.getStartFigure().isMoved || move.getEndFigure().isMoved) return false;
        var nVector = new Coordinate(Math.abs(dx)/dx, Math.abs(dy)/dy);
        for (var iVector = move.getStartCoordinate();
             iVector.x() != move.getEndCoordinate().x() && iVector.y() != move.getEndCoordinate().y();
             iVector = new Coordinate(iVector.x() + nVector.x(), iVector.y() + nVector.y())) {
            if (Objects.nonNull(board.getSquare(iVector).getFigure())) return false;
        }
        //TODO add check of king under attack on the way
        return true;
    };
}
