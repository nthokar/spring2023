package com.nthokar.spring2023.main.application.interfaces;

import com.nthokar.spring2023.main.domain.chess.game.Game;

public interface GameService {
    Game getGame(String id);
}
