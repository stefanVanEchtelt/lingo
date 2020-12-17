package com.lingo.project.game.core.domain;

import org.junit.jupiter.api.Test;

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
}