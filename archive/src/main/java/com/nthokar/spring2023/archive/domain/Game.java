package com.nthokar.spring2023.archive.domain;

import java.util.List;

public interface Game {
    String getId();
    List<? extends Move> getMoves();
    List<? extends Position> getPositions();
    Opening extractOpening();
    String getWhitePlayer();
    String getBlackPlayer();
}
