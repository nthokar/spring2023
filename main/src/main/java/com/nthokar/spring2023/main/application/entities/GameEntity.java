package com.nthokar.spring2023.main.application.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@Table(name = "games")
@AllArgsConstructor
@NoArgsConstructor
public class GameEntity {
    @Id
    private Long id;
    @ManyToOne
    private UserEntity whitePlayer;
    @ManyToOne
    private UserEntity blackPlayer;

    @OneToMany
    public List<MoveEntity> moves;
}
