package com.lingo.project.game.core.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    public void zeroTriesNextWord5LettersCheck() {
        Game game = new Game();

        int wordSize = game.nextWordSize();

        assertEquals(5, wordSize);
    }
}