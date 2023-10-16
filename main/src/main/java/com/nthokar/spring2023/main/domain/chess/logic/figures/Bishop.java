package com.nthokar.spring2023.main.domain.chess.logic.figures;

import com.nthokar.spring2023.main.domain.chess.logic.rules.MoveRules;
import com.nthokar.spring2023.main.domain.chess.logic.rules.Rule;
import java.awt.*;

public class Bishop extends Figure {
    /**
     * initialize new Bishop
     * @param color color of bishop
     * @param rules rules applied to bishop
     */
    public Bishop(Color color, Rule[] rules) {
        super("bishop", color, rules);
    }

    /**
     * initialize new Bishop with classic rules
     * @param color color of bishop
     */
    public static Bishop getClassic(Color color){
        return new Bishop(color, new Rule[] {MoveRules.bishopMoveTemplate} );
    }
}
