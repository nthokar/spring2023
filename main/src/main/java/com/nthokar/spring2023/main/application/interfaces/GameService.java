package com.nthokar.spring2023.main.application.interfaces;

import com.nthokar.spring2023.main.application.Game;

public interface GameService {

    Game createNewGame(Game.Builder builder);
    Game getGame(String id);
}
