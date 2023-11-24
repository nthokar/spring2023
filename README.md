# spring2023 - a simple web api for chess 

---
            +-----+-----+-----+-----+-----+-----+-----+-----+
            |  r  |  b  |  n  |  q  |  k  |  n  |  b  |  r  | 8
            +-----+-----+-----+-----+-----+-----+-----+-----+
            |  p  |  p  |  p  |  p  |  p  |  p  |  p  |  p  | 7
            +-----+-----+-----+-----+-----+-----+-----+-----+
            |     |     |     |     |     |     |     |     | 6
            +-----+-----+-----+-----+-----+-----+-----+-----+
            |     |     |     |     |     |     |     |     | 5
            +-----+-----+-----+-----+-----+-----+-----+-----+
            |     |     |     |     |  P  |     |     |     | 4
            +-----+-----+-----+-----+-----+-----+-----+-----+
            |     |     |     |     |     |     |     |     | 3
            +-----+-----+-----+-----+-----+-----+-----+-----+
            |  P  |  P  |  P  |  P  |     |  P  |  P  |  P  | 2
            +-----+-----+-----+-----+-----+-----+-----+-----+
            |  R  |  N  |  B  |  Q  |  K  |  B  |  N  |  R  | 1
            +-----+-----+-----+-----+-----+-----+-----+-----+
               a     b     c     d     e     f     g     h

## About

---

is a web api for chess websites, that allows a lot of
possibilities of games variations.

The site will provide the opportunity to play with other players, with bots, and also participate in tournaments.
Also on this website there will be educational materials, for example, analysis of games and viewing of the most interesting
games from history.

## Features(IN PLAN)

---

### Api affords:
- User authorization in and authorizations with via relative databases, like postgres.
- Starting and processing chess games.
- queuing users games and merging it. 
- Elo's system of players rank.
- Analyzing users matches and chess positions.

### Data processing

- User accounts manegmenting via roles model.
  - Register users.
  - Change user's details.
  - <p>...
- Finding opponent by game based on filters.
- Elo's rating update.

- Processing data of games in archive.
    - Saving users games to database.
    - Afford statistics of games
    - [More details](INFO.md)
  
