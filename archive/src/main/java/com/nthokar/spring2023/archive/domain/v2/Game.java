package com.nthokar.spring2023.archive.domain.v2;

import com.nthokar.spring2023.archive.domain.Move;

import java.util.List;

public interface Game {
    List<? extends Move> getMoves();
    List<? extends Position> getPositions();
}
