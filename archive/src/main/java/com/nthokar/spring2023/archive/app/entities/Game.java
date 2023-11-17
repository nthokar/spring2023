package com.nthokar.spring2023.archive.app.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;


@Entity
public class Game implements com.nthokar.spring2023.archive.domain.v2.Game {
    @Id
    private Long id;

    @OneToMany
    List<Move> moves;

    @OneToMany
    List<Position> positions;

    public Long getId() {
        return this.id;
    }

    public List<Move> getMoves() {
        return this.moves;
    }

    public List<Position> getPositions() {
        return this.positions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}
