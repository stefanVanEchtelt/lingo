package com.lingo.project.game.core.ports.resource;

import com.lingo.project.game.core.domain.Game;
import com.lingo.project.game.core.domain.GameStatus;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.Objects;

@Getter
public class GameResource {
    private Long id;
    private GameStatus gameStatus;
    private Date createdAt;
    private RoundResource currentRound;
    private int currentScore;

    public GameResource(Game game) {
        this.id = game.getId();
        this.gameStatus = game.getGameStatus();
        this.createdAt = game.getCreatedAt();
        this.currentScore = game.getScore();
        this.currentRound = new RoundResource(game.getCurrentRound());
    }
}
