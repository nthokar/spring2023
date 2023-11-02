package com.nthokar.spring2023.main.domain;

public interface User {

    String getId();

    String getName();
    Integer getElo();
    void setElo(Integer elo);
}