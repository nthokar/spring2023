package com.nthokar.spring2023.archive.domain;

import java.util.List;

public interface IOpening {
    String getName();
    List<IMove> getMoves();
    List<IPosition> getKeyPositions();
}
