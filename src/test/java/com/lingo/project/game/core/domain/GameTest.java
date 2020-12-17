package com.lingo.project.game.core.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    void zeroRoundsNextWord5LettersCheck() {
        Game game = new Game();

        int wordSize = game.nextWordSize();

        assertEquals(5, wordSize);
    }

    @Test
    void oneRoundNextWord5LettersCheck() {
        Game game = new Game();
        game.addRound(new Round());

        int wordSize = game.nextWordSize();

        assertEquals(6, wordSize);
    }

    @Test
    void twoRoundNextWord5LettersCheck() {
        Game game = new Game();
        game.addRound(new Round());
        game.addRound(new Round());

        int wordSize = game.nextWordSize();

        assertEquals(7, wordSize);
    }

    @Test
    void treeRoundNextWord5LettersCheck() {
        Game game = new Game();
        game.addRound(new Round());
        game.addRound(new Round());
        game.addRound(new Round());

        int wordSize = game.nextWordSize();

        assertEquals(5, wordSize);
    }

    @Test
    void checkCorrectWord() {
        Game game = new Game();
        game.setScore(3);

        game.correctWord();

        assertEquals(4, game.getScore());
    }

    @Test
    void checkGameEnd() {
        Game game = new Game();

        game.end();

        assertEquals(GameStatus.FINISHED, game.getGameStatus());
    }

    @Test
    void checkCurrentRoundsNoRoundsGame() {
        Game game = new Game();

        Round round = game.getCurrentRound();

        assertNull(round);
    }

    @Test
    void checkCurrentRoundsGame() {
        Game game = new Game();
        Round round = new Round();
        game.addRound(round);

        Round currentRound = game.getCurrentRound();

        assertEquals(round, currentRound);
    }

    @Test
    void checkEmptyConstructor() {
        Game game = new Game();

        assertNull(game.getId());
        assertEquals(GameStatus.PROGRESS, game.getGameStatus());
        assertEquals(new ArrayList<Round>(), game.getRounds());
        assertNull(game.getCreatedAt());
        assertEquals(0, game.getScore());
        assertNull(game.getUsername());
    }
}