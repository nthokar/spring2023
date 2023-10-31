package com.nthokar.spring2023.main.application.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@Table(name = "games")
//@SecondaryTable(name = "users_games")
@AllArgsConstructor
@NoArgsConstructor
public class GameEntity {
    @Id
    @Column(name = "game_id")
    private String id;
//    @ManyToOne
//    @JoinColumn(name = "white_player", foreignKey =  @ForeignKey(
//            foreignKeyDefinition = "FOREIGN KEY (white_player) REFERENCES users" ))
//    private UserEntity whitePlayer;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToOne()
    @JoinColumn(name = "black_player_id")
    private UserEntity blackPlayer;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToOne()
    @JoinColumn(name = "white_player_id")
    private UserEntity whitePlayer;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    public List<MoveEntity> moves;
}
