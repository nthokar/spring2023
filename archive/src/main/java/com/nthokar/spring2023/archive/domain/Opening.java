package com.nthokar.spring2023.archive.domain;

import java.util.List;

public interface Opening {
    String getName();
    List<Move> getMoves();
    List<Position> getKeyPositions();
}
