package com.nthokar.spring2023.userauth.application.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;


@Table(name="users")
@Entity
@Data
public class User {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="elo")
    private Integer elo;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}