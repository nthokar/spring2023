package com.nthokar.spring2023.main.application.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements com.nthokar.spring2023.main.domain.User {

    @Id String email;
    @Setter Integer elo;
    String name;
    @OneToMany
    List<GameEntity> games;
}
