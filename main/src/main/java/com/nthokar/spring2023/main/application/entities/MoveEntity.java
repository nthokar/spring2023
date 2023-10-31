package com.nthokar.spring2023.main.application.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "moves")
@AllArgsConstructor
@NoArgsConstructor
public class MoveEntity {
    @Id
    private String id;
    @ManyToOne
    private GameEntity game;
    private Integer number;
    private String move;

}
