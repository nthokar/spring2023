package com.nthokar.spring2023.main.application.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "users")
//@SecondaryTable(name = "users_games")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements com.nthokar.spring2023.main.domain.User {

    @Column(name = "email")
    @Id String id;
    @Setter Integer elo;
    String name;
//    @JoinColumn(name = "game_id", foreignKey =  @ForeignKey(
//            foreignKeyDefinition = "FOREIGN KEY (game_id) REFERENCES games" ))
}
