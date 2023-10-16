package com.nthokar.spring2023.main.domain.chess.logic.figures;

import com.nthokar.spring2023.main.domain.chess.logic.rules.MoveRules;
import com.nthokar.spring2023.main.domain.chess.logic.rules.Rule;

import java.awt.*;

public class Rook extends Figure {
    /**
     * initialize new Rook
     * @param color color of Rook
     * @param rules rules applied to Rook
     */
    Rook(Color color, Rule[] rules) {
        super("rook", color, rules);
    }
    /**
     * initialize new Rook with classic rules
     * @param color color of Rook
     */
    public static Rook getClassic(Color color) {
        return new Rook(color, new Rule[]{MoveRules.rookMoveTemplate});
    }
}
