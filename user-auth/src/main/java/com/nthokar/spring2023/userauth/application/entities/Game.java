package com.nthokar.spring2023.userauth.application.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class Game {
    @Id
    @Column(name = "game_id")
    private String id;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToOne()
    @JoinColumn(name = "black_player_id")
    private User blackPlayer;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToOne()
    @JoinColumn(name = "white_player_id")
    private User whitePlayer;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    public List<Move> moves;
}
