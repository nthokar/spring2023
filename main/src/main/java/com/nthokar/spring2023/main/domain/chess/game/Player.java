package com.nthokar.spring2023.main.domain.chess.game;

import lombok.Getter;
import lombok.Setter;
@Getter
public class Player {
    String id;
    @Setter
    Integer elo;
    String name;
}