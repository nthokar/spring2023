package com.nthokar.spring2023.main.domain;

public interface User {

    String getEmail();

    String getName();
    Integer getElo();
    void setElo(Integer elo);
}