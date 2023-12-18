package com.nthokar.spring2023.main.application.DTO.archive;
import com.nthokar.spring2023.main.domain.chess.game.Game;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class GameDTO {
    private String id;

    List<MoveDTO> moves;

    List<PositionDTO> positions;

    String whitePlayer;
    String blackPlayer;
    public GameDTO(Game game) {
        throw new RuntimeException();
    }
}
