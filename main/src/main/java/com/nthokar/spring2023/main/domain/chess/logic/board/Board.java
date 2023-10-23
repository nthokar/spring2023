package com.nthokar.spring2023.main.domain.chess.logic.board;

import com.nthokar.spring2023.main.domain.chess.logic.Move;
import com.nthokar.spring2023.main.domain.chess.logic.rules.Rule;
import com.nthokar.spring2023.main.domain.chess.logic.figures.*;
import com.nthokar.spring2023.main.domain.chess.logic.Coordinate;

import com.nthokar.spring2023.main.domain.chess.logic.rules.MoveRules;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Board {

   /**
    * current state
    */
   @Getter
   State state;
   //current state of the game
   public enum State {
      //game is processing
      IN_GAME,
      //game paused
      PAUSE,
      //game ended in draw
      DRAW,
      MATE,
      CHECK_MATE,
      SURRENDERED
   }
   /**
    * array of game general rules.
    * That array initialized once time before game start,
    * and cannot to be changed.
    */
   final Rule[] rules;

   /**
    * array of squares at board
    * That array initialized once time before game start,
    * and cannot to be change.
    */
   final Square[][] squares;

   /**
    * history of moves
    */
   final MovesHistory history;

   /**
    * Builder class created for initialize new Board instance
    */
   public static class Builder {
      /**
       * array of game general rules.
       */
      private List<Rule> rules;
      /**
       * array of squares at board
       */
      private final Square[][] squares;

      /**
       * Constructor that initialized Board.Builder. Init squares array like at default chess board.
       */
      public Builder() {
         rules = new ArrayList<>();
         squares = new Square[8][8];
         for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
               var currentCell = new Square( (j + i) % 2 == 0 ? Color.WHITE : Color.BLACK, new Coordinate(j+1, i+1));
               squares[j][i] = currentCell;
            }
         }
      }

      /**
       *  Set figure at board
       *
       * @param figure Figure that will be placed at specified coordinates
       * @param coordinate record of (int x, int y)
       * @return Builder instance
       */
      public Builder setFigure(Figure figure, Coordinate coordinate){
         var square = this.squares[coordinate.x() - 1][coordinate.y() - 1];
         square.setFigure(figure);
         return this;
      }

      /**
       *  set new rule
       * @param rule game rule that would be applied to a game
       * @return Builder instance
       */
      public Builder setRule(Rule rule){
         rules.add(rule);
         return this;
      }

      /**
       *  set default chess game rules
       * @return Builder instance
       */
      public Builder setDefaultRules(){
         rules = Arrays.stream(new Rule[] {MoveRules.checkWarning, MoveRules.nullMove}).toList();
         return this;
      }

      /**
       * set default chess position
       * @return Builder instance
       */
      public Builder setDefaultPosition(){
         int i = 1;
         for (int j = 0; j < 8; j++){
            squares[j][i].setFigure(Pawn.getClassic(Color.WHITE));
         }
         i = 6;
         for (int j = 0; j < 8; j++){
            squares[j][i].setFigure(Pawn.getClassic(Color.BLACK));
         }
         squares[0][7].setFigure(Rook.getClassic(Color.BLACK));
         squares[1][7].setFigure(Knight.getClassic(Color.BLACK));
         squares[2][7].setFigure(Bishop.getClassic(Color.BLACK));
         squares[3][7].setFigure(Queen.getClassic(Color.BLACK));
         squares[4][7].setFigure(King.getClassic(Color.BLACK));
         squares[5][7].setFigure(Bishop.getClassic(Color.BLACK));
         squares[6][7].setFigure(Knight.getClassic(Color.BLACK));
         squares[7][7].setFigure(Rook.getClassic(Color.BLACK));

         squares[0][0].setFigure(Rook.getClassic(Color.WHITE));
         squares[1][0].setFigure(Knight.getClassic(Color.WHITE));
         squares[2][0].setFigure(Bishop.getClassic(Color.WHITE));
         squares[3][0].setFigure(Queen.getClassic(Color.WHITE));
         squares[4][0].setFigure(King.getClassic(Color.WHITE));
         squares[5][0].setFigure(Bishop.getClassic(Color.WHITE));
         squares[6][0].setFigure(Knight.getClassic(Color.WHITE));
         squares[7][0].setFigure(Rook.getClassic(Color.WHITE));
         return this;
      }

      /**
       * set default chess game rules and chess position
       * @return Builder instance
       */
      public Builder setDefault(){
         setDefaultRules().setDefaultPosition();
         return this;
      }

      /**
       * initialize board with specified at builder fields
       * @return Board
       */
      public Board build(){
         return new Board(rules.toArray(new Rule[0]), squares, new MovesHistory());
      }

   }
   /**
    * returns square of board with specified coordinates
    * @param coordinate
    * @return square with specified coordinates
    */
   public Square getSquare(Coordinate coordinate) {
      if (coordinate.x() > 8 || coordinate.y() > 8) throw new RuntimeException("square out of board");
      return squares[coordinate.x() - 1][coordinate.y() - 1];
   }

   /**
    * get all squares with figures of a certain color
    * @param color
    * @return list of squares
    */
   public List<Square> getFigureSquaresByColor(Color color){
      ArrayList<Square> figures = new ArrayList<>();
      for (var i:squares){
         for (var square:i){
            if (square.getFigure() != null)
               figures.add(square);
         }
      }
      if (Objects.isNull(color)){
         return figures;
      }
      return figures.stream()
              .filter(square -> square.getFigure().color == color)
              .collect(Collectors.toList());
   }
   /**
    * make move if it satisfies the rules
    * @param move
    * @return true if move has been made and false if not
    */
   public boolean moveFigureIfLegal(Move move) {
      var isMoveLegal = isMoveLegal(move);
      if (isMoveLegal) {
         moveForce(move);
         history.addMove(move);
      }
      return isMoveLegal;
   }

   /**
    * defines is move legal by specified board and figures rules
    * @param move
    * @return true if move is legal and false if not
    */
   public boolean isMoveLegal(Move move){
      var startSquare = getSquare(move.getStartCoordinate());
      move = new Move(startSquare, getSquare(move.getEndCoordinate()));
      if (Objects.isNull(startSquare.getFigure())) return false;
      boolean flag = isMoveLegalByFiguresRules(move);
      for (Rule rule: rules){
         flag &= rule.apply(move, this);
      }
      return flag;
   }

   /**
    * calculates whether there will be check after the move for color that made move
    * @param move
    * @return true if king would be checked and false if not
    */
   public boolean isCheckAfterMove(Move move){
      var startSquare = getSquare(move.getStartCoordinate());
      var kingColor = startSquare.figure.color;
      return testAfterMove(this::isCheck, kingColor, move);
   }

   private boolean isCheck(Color color) {
      var enemiesFiguresSquares = getFigureSquaresByColor(
              color == Color.BLACK ? Color.WHITE : Color.BLACK);
      for (var square: enemiesFiguresSquares) {
         var enemyCheckMove = new Move(square, getKingSquare(color));
         if (isMoveLegalByFiguresRules(enemyCheckMove)) {
            return true;
         }
      }
      return false;
   }

   public void isCheckMate(Color color) {
      if (isCheck(color))
         if (!isAnyMove(color)) {
            state = State.CHECK_MATE;
         }
   }
   
   private boolean isAnyMove(Color color) {
      var figureSquares = getFigureSquaresByColor(color);
      for (var i = 1; i < 9; i++){
         for (var j = 1; j < 9; j++){
            for (var figure:figureSquares) {
               var move = new Move(figure.coordinate, new Coordinate(i, j));
               if (isMoveLegalByFiguresRules(move)){
                  if (!isCheckAfterMove(move))
                     return true;
               }
            }
         }
      }
      return false;
   }
   /**
    * print board
    */
   public void print(){
      for (int i = 7; i >= 0; i--){
         for (int j = 0; j < 8; j++){
            System.out.print(squares[j][i].prettyToString());
         }
         System.out.printf("%s%n", " " + (i+1));
      }
      System.out.println(" a  b  c  d  e  f  g  h");
   }

   /**
    * defines is move legal by specified figures rules
    * @param move
    * @return true if move is legal and false if not
    */
   private boolean isMoveLegalByFiguresRules(Move move){
      var startSquare = getSquare(move.getStartCoordinate());
      if (Objects.isNull(startSquare.getFigure())) return false;
      boolean flag = false;
      var allRules = startSquare.getFigure().rules;
      for (Rule rule: allRules){
         //todo fix
         flag |= (rule.apply(move, this) && MoveRules.nullMove.apply(move, this) && MoveRules.friendlyFire.apply(move, this));
      }
      return flag;
   }

   /**
    * test predicate after make specified move, and undo that
    * @param f predicate with move param
    * @param move
    * @return return predicate result after move
    */
   private boolean testAfterMove(Predicate<Color> f, Color color, Move move) {
      var startSquare = getSquare(move.getStartCoordinate());
      var endSquare = getSquare(move.getEndCoordinate());
      var endFigure = endSquare.getFigure();

      startSquare.figure.isMoved = true;
      endSquare.figure = startSquare.figure;
      startSquare.figure = null;

      var result = f.test(color);

      startSquare.figure = endSquare.getFigure();
      endSquare.figure = endFigure;
      startSquare.figure.isMoved = false;
      return result;
   }

   /**
    * make move
    * @param move
    */
   private void moveForce(Move move) {
      var startSquare = getSquare(move.getStartCoordinate());
      var endSquare = getSquare(move.getEndCoordinate());
      startSquare.figure.isMoved = true;

      endSquare.figure = startSquare.figure;
      startSquare.figure = null;
   }

   /**
    *
    * @param color king color
    * @return king's square of specified color
    */
   private Square getKingSquare(Color color) {
      var figures = getFigureSquaresByColor(color);
      for (var square : figures) {
         if (square.getFigure() instanceof King)
            return square;
      }
      throw new RuntimeException("there is no king");
   }
}
