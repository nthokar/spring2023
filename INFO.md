# Game storage

---

- ## First way

    <b>Archive service</b> have own rabbit queue, and reads from it every new players move.
When game is complete, archive service write a new game based on played moves.


- ## Second way

    When game ends, <b>main service</b> send to <b>Archive service</b> a complete DTO of game, that's needs to save.


# Game storage format

---

Games stores as sequential set of positions and moves. This format allows
Greater flexibility in data processing, but saving unnecessary information.

## Position format

---

Position stores in string, which format looks like:
    <p> rnbqkbnr8p32.8PRNBQKBNR

### Explanation of encoding

---
Capitalize of symbol represent color of figure, if letter is lowercase, then figure is white.
Slash mean begin of next row of board. The numeric suffix represents the number of repeated characters.

#### alphabet

- . for empty
- P for pawn
- N for knight
- B for bishop
- R for rook
- Q for queen
- K for king
