package com.nthokar.spring2023.archive.app.entities;

import com.nthokar.spring2023.archive.domain.IPosition;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Position implements IPosition {
    @Id
    String position;
    Integer index;
}
