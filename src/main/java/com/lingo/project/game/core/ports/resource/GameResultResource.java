package com.lingo.project.game.core.ports.resource;

import com.lingo.project.game.core.domain.Game;
import com.lingo.project.game.core.domain.GameStatus;
import lombok.Data;

import java.util.Date;

@Data
public class GameResultResource {
    private Long id;
    private GameStatus gameStatus;
    private Date createdAt;
    private int currentScore;

    public GameResultResource(Game game) {
        this.id = game.getId();
        this.gameStatus = game.getGameStatus();
        this.createdAt = game.getCreatedAt();
        this.currentScore = game.getScore();
    }
}
