package com.nthokar.spring2023.main.domain.chess.logic.figures;

import com.nthokar.spring2023.main.domain.chess.logic.rules.Rule;
import com.nthokar.spring2023.main.domain.chess.logic.rules.MoveRules;

import java.awt.*;

public class Queen extends Figure {
    /**
     * initialize new Queen
     * @param color color of Queen
     * @param rules rules applied to Queen
     */
    public Queen(Color color, Rule[] rules) {
        super("queen", color, rules);
    }
    /**
     * initialize new Queen with classic rules
     * @param color color of Queen
     */
    public static Queen getClassic(Color color) {
        return new Queen(color, new Rule[] {MoveRules.queenMoveTemplate});
    }
}