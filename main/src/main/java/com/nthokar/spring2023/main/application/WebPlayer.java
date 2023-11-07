package com.nthokar.spring2023.main.application;

import com.nthokar.spring2023.main.application.entities.UserEntity;
import com.nthokar.spring2023.main.domain.chess.game.MoveStream;
import com.nthokar.spring2023.main.domain.chess.game.Player;
import com.nthokar.spring2023.main.domain.chess.logic.Move;

import java.io.*;

public class WebPlayer extends UserEntity  {
    public WebPlayer(UserEntity userEntity) {
        super(userEntity.getId(), userEntity.getElo(), userEntity.getName() /*userEntity.getGames()*/);
    }
}
