package com.nthokar.spring2023.main.domain.chess.logic.figures;

import com.nthokar.spring2023.main.domain.chess.logic.rules.Rule;

import java.awt.*;

public abstract class Figure {
    /**
     * name of figure. Name initialize once and cannot be changed
     */
    public final String name;
    /**
     * color of figure. Color initialize once and cannot be changed
     */
    public final Color color;
    /**
     * material equivalent of figure. Material initialize once and cannot be changed
     */
    public final int material;
    /**
     * array of rules for figure. Rules initialize once and cannot be changed
     */
    public final Rule[] rules;
    /**
     * boolean field that answer to question did figure move
     */
    public boolean isMoved = false;

    Figure(String name, Color color, Rule[] rules){
        this.name = name;
        this.color = color;
        this.material = getMaterial();
        this.rules = rules;
    }

    /**
     *
     * @return material equivalent of figure by its name
     */
    private int getMaterial(){
        switch (name){
            case "pawn":
                return 1;
            case "knight", "bishop":
                return 3;
            case "rook":
                return 5;
            case "queen":
                return 9;
        }
        return 0;
    }

    @Override
    public String toString() {
        return color == Color.BLACK ?
                String.valueOf(name.charAt(0)).toUpperCase() : String.valueOf(name.charAt(0)).toLowerCase();
    }
}
