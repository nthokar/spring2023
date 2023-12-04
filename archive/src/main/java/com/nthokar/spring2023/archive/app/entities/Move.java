package com.nthokar.spring2023.archive.app.entities;

import com.nthokar.spring2023.archive.domain.IMove;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Move implements IMove {
    @Id
    private Long id;
    Integer index;

    String move;
    Float spentTime;
}
