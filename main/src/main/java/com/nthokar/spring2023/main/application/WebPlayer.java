package com.nthokar.spring2023.main.application;

import com.nthokar.spring2023.main.application.entities.UserEntity;
import com.nthokar.spring2023.main.domain.chess.game.Player;
import com.nthokar.spring2023.main.domain.chess.logic.Move;

import java.io.*;

public class WebPlayer extends UserEntity implements Player  {

    public ObjectInputStream inputStream;

    public WebPlayer(UserEntity userEntity) {
        super(userEntity.getEmail(), userEntity.getElo(), userEntity.getName(), userEntity.getGames());
    }

    @Override
    public Move getMove() {
        try {
            return (Move) inputStream.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
