package com.nthokar.spring2023.archive.app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Position implements com.nthokar.spring2023.archive.domain.v2.Position {
    @Id
    private Long id;
    Integer index;
    String position;
}
