package com.nthokar.spring2023.archive.domain;

import java.util.List;

public interface IGame {
    String getId();
    List<? extends IMove> getMoves();
    List<? extends IPosition> getPositions();
    IOpening extractOpening();
    String getWhitePlayer();
    String getBlackPlayer();
}
