package com.nthokar.spring2023.main.domain.chess.logic.figures;

import com.nthokar.spring2023.main.domain.chess.logic.rules.Rule;
import com.nthokar.spring2023.main.domain.chess.logic.rules.MoveRules;

import java.awt.*;

public class Pawn extends Figure {
    /**
     * initialize new Pawn
     * @param color color of Pawn
     * @param rules rules applied to Pawn
     */
    Pawn(Color color, Rule[] rules) {
        super("pawn", color, rules);
    }
    /**
     * initialize new Pawn with classic rules
     * @param color color of Pawn
     */
    public static Figure getClassic(Color color) {
        return new Pawn(color, color == Color.WHITE ?
                new Rule[] { MoveRules.whitePawnMoveTemplate, MoveRules.whitePawnJumpTemplate, MoveRules.whitePawnCapturingTemplate} :
                new Rule[] { MoveRules.blackPawnMoveTemplate, MoveRules.blackPawnJumpTemplate, MoveRules.blackPawnCapturingTemplate} );
    }
}