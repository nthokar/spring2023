package com.nthokar.spring2023.main.application.entities;

import com.nthokar.spring2023.main.domain.chess.game.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends Player {

    @Id String id;
    @Setter Integer elo;
    String name;
}
