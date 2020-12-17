package com.lingo.project.game.core.ports.resource;

import com.lingo.project.game.core.domain.Game;
import com.lingo.project.game.core.domain.GameStatus;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class GameResultResourceTest {

    @Test
    void testConstructor() {
        Game game = Game.builder().id((long) 1).gameStatus(GameStatus.FINISHED)
                .createdAt(new Timestamp(System.currentTimeMillis())).score(1).build();
        GameResultResource gameResultResource = new GameResultResource(game);

        assertEquals(game.getId(), gameResultResource.getId());
        assertEquals(game.getGameStatus(), gameResultResource.getGameStatus());
        assertEquals(game.getCreatedAt(), gameResultResource.getCreatedAt());
        assertEquals(game.getScore(), gameResultResource.getCurrentScore());
    }
}