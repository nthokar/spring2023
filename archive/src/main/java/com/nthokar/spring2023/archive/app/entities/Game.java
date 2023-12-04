package com.nthokar.spring2023.archive.app.entities;


import com.nthokar.spring2023.archive.domain.IGame;
import com.nthokar.spring2023.archive.domain.IOpening;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.List;


@Entity
public class Game implements IGame {
    @Id
    private String id;

    @OneToMany
    List<Move> moves;

    @ManyToMany
    List<Position> positions;

    String whitePlayer;
    String blackPlayer;

    @Override
    public String getId() {
        return this.id;
    }
    @Override
    public List<Move> getMoves() {
        return this.moves;
    }

    @Override
    public List<Position> getPositions() {
        return this.positions;
    }

    @Override
    public IOpening extractOpening() {
        return null;
    }

    @Override
    public String getWhitePlayer() {
        return null;
    }

    @Override
    public String getBlackPlayer() {
        return null;
    }
}
