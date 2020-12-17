package com.lingo.project.game.core.ports.resource;

import com.lingo.project.game.core.domain.Game;
import com.lingo.project.game.core.domain.GameStatus;
import com.lingo.project.game.core.domain.Round;
import com.lingo.project.game.core.domain.Try;
import com.lingo.project.word.core.domain.Word;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameResourceTest {

    @Test
    void testConstructor() {
        List<Round> rounds = new ArrayList<Round>();
        Word word = Word.builder().value("testen").build();
        Round round = Round.builder().id((long) 2).tries(new ArrayList<Try>()).word(word).build();
        rounds.add(round);
        Game game = Game.builder().id((long) 1).gameStatus(GameStatus.FINISHED)
                .createdAt(new Timestamp(System.currentTimeMillis())).score(1)
                .rounds(rounds).build();
        RoundResource roundResource = new RoundResource(round);
        GameResource gameResource = new GameResource(game);

        assertEquals(game.getId(), gameResource.getId());
        assertEquals(game.getGameStatus(), gameResource.getGameStatus());
        assertEquals(game.getCreatedAt(), gameResource.getCreatedAt());
        assertEquals(game.getScore(), gameResource.getCurrentScore());
        assertEquals(roundResource, gameResource.getCurrentRound());
    }
}