package com.nthokar.spring2023.main.domain.chess.logic.figures;

import com.nthokar.spring2023.main.domain.chess.logic.rules.MoveRules;
import com.nthokar.spring2023.main.domain.chess.logic.rules.Rule;

import java.awt.*;

public class King extends Figure {
    /**
     * initialize new King
     * @param color color of King
     * @param rules rules applied to King
     */
    public King(Color color, Rule[] rules) {
        super("king", color, rules);
    }
    /**
     * initialize new King with classic rules
     * @param color color of King
     */
    public static Figure getClassic(Color color) {
        return new King(color, new Rule[] {MoveRules.kingMoveTemplate});
    }
}