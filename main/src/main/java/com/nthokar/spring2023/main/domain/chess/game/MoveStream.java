package com.nthokar.spring2023.main.domain.chess.game;

import com.nthokar.spring2023.main.domain.chess.logic.Coordinate;
import com.nthokar.spring2023.main.domain.chess.logic.Move;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class MoveStream extends BufferedInputStream {
    InputStream inputStream;
    Scanner scanner;

    public MoveStream(InputStream in) {
        super(in);
        inputStream = in;
        scanner = new Scanner(in);
    }
    public Move getMove(){
        try {
            return readMove(scanner.nextLine());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //parse move
    private Move readMove(String str){
        try {
            var from = readCoordinate(str.substring(0,2));
            var to = readCoordinate(str.substring(2));
            return new Move(from, to);
        }
        catch (Exception e) {
            throw new RuntimeException("wrong input");
        }
    }
    private Coordinate readCoordinate(String str) throws IllegalAccessException {
        if (str.length() != 2)
            throw new IllegalAccessException();
        return new Coordinate((int)str.charAt(0) - (int)'a' + 1, Character.getNumericValue(str.charAt(1)));
    }
}
