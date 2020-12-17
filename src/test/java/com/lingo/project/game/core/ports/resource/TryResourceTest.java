package com.lingo.project.game.core.ports.resource;

import com.lingo.project.game.core.domain.Game;
import com.lingo.project.game.core.domain.GameStatus;
import com.lingo.project.game.core.domain.Round;
import com.lingo.project.game.core.domain.Try;
import com.lingo.project.word.core.domain.LetterFeedback;
import com.lingo.project.word.core.domain.Word;
import com.lingo.project.word.core.domain.WordDifferenceStatus;
import com.lingo.project.word.core.domain.WordFeedback;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TryResourceTest {

    @Test
    void testTryResourceCreation() {
        List<Round> rounds = new ArrayList<Round>();
        Word word = Word.builder().value("testen").build();
        Round round = Round.builder().id((long) 2).tries(new ArrayList<Try>()).word(word).build();
        rounds.add(round);
        Game game = Game.builder().id((long) 1).gameStatus(GameStatus.FINISHED)
                .createdAt(new Timestamp(System.currentTimeMillis())).score(1)
                .rounds(rounds).build();
        GameResource gameResource = new GameResource(game);
        List<LetterFeedback> letterFeedbackList = new ArrayList<LetterFeedback>();
        WordFeedback wordFeedback = new WordFeedback(WordDifferenceStatus.CORRECT, letterFeedbackList);
        TryResource tryResource = new TryResource(game, wordFeedback);

        assertEquals(wordFeedback, tryResource.getWordFeedback());
        assertEquals(gameResource, tryResource.getGame());
    }
}