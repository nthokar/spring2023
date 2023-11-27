package com.nthokar.spring2023.archive.app.entities;


import com.nthokar.spring2023.archive.domain.Opening;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;


@Entity
public class Game implements com.nthokar.spring2023.archive.domain.Game {
    @Id
    private String id;

    @OneToMany
    List<Move> moves;

    @OneToMany
    List<Position> positions;

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
    public Opening extractOpening() {
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
