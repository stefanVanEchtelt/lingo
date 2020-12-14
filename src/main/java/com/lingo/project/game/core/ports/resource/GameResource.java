package com.lingo.project.game.core.ports.resource;

import com.lingo.project.game.core.domain.Game;
import com.lingo.project.game.core.domain.GameStatus;
import lombok.Data;

import java.util.Date;

@Data
public class GameResource {
    private Long id;
    private GameStatus gameStatus;
    private Date createdAt;

    public GameResource(Game game) {
        this.id = game.getId();
        this.gameStatus = game.getGameStatus();
        this.createdAt = game.getCreatedAt();

        System.out.println("DDDDDD");
        System.out.println(game.getCurrentRound());
    }
}
