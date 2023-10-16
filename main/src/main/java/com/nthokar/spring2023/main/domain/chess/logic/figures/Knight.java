package com.nthokar.spring2023.main.domain.chess.logic.figures;

import com.nthokar.spring2023.main.domain.chess.logic.rules.MoveRules;
import com.nthokar.spring2023.main.domain.chess.logic.rules.Rule;

import java.awt.*;

public class Knight extends Figure {
    /**
     * initialize new Knight
     * @param color color of Knight
     * @param rules rules applied to Knight
     */
    public Knight(Color color, Rule[] rules) {
        super("knight", color, rules);
    }
    /**
     * initialize new Knight with classic rules
     * @param color color of Knight
     */
    public static Knight getClassic(Color color) {
        return new Knight(color, new Rule[] {MoveRules.knightMoveTemplate});
    }
}