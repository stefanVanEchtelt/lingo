package com.lingo.project.game.core.ports.resource;

import com.lingo.project.game.core.domain.Game;
import com.lingo.project.game.core.domain.GameStatus;
import com.lingo.project.game.core.domain.Round;
import com.lingo.project.game.core.domain.Try;
import com.lingo.project.word.core.domain.Word;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RoundResourceTest {
    @Test
    void testConstructor() {
        Word word = Word.builder().value("testen").build();
        Round round = Round.builder().id((long) 1).tries(new ArrayList<Try>()).word(word).build();
        RoundResource roundResource = new RoundResource(round);

        assertEquals(round.getId(), roundResource.getRoundId());
        assertEquals(word.getValue().length(), roundResource.getWorthLength());
        assertEquals(0, roundResource.getAmountOfTries());
    }
}