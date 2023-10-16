package com.nthokar.spring2023.main.domain.chess.logic.board;

import com.nthokar.spring2023.main.domain.chess.logic.Coordinate;
import com.nthokar.spring2023.main.domain.chess.logic.figures.Figure;
import lombok.*;

import java.awt.*;
import java.util.Objects;

@RequiredArgsConstructor
@AllArgsConstructor
public class Square {

    /**
     * figure that now set at this square.
     */
    @Getter
    @Setter(AccessLevel.PACKAGE)
    Figure figure;

    /**
     * color of square. Color initialize once and cannot be changed
     */
    public final Color color;
    /**
     * coordinate of square. Coordinate initialize once and cannot be changed
     */
    public final Coordinate coordinate;

    /**
     * @return string for board.print()
     */
    public String prettyToString() {
        return " " + (Objects.nonNull(figure) ? figure : " ") + " ";
    }
}
