package com.nthokar.spring2023.main.application;

import com.nthokar.spring2023.main.domain.chess.game.Player;
import com.nthokar.spring2023.main.domain.chess.logic.Move;
import lombok.RequiredArgsConstructor;

import java.io.*;

public class WebPlayer extends User implements Player {

    public ObjectInputStream inputStream;

    public WebPlayer(User user) {
        super(user.id, user.name, user.elo);
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
