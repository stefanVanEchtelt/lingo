package com.lingo.project.game.core.domain;

import com.lingo.project.word.core.domain.Word;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TryTest {

    @Test
    void checkEmptyConstruct() {
        Try tryInst = new Try();

        assertNull(tryInst.getId());
        assertNull(tryInst.getRound());
        assertNull(tryInst.getCreatedAt());
        assertNull(tryInst.getValue());
    }

    @Test
    void checkNoWordIsCorrect() {
        Try tryInst = Try.builder().build();

        boolean isCorrect = tryInst.isCorrect();

        assertFalse(isCorrect);
    }

    @Test
    void checkIsCorrect() {
        Word word = Word.builder().value("testen").build();
        Round round = Round.builder().word(word).build();
        Try tryInst = Try.builder().value("testen").round(round).build();

        boolean isCorrect = tryInst.isCorrect();

        assertTrue(isCorrect);
    }

    @Test
    void checkIsNotCorrect() {
        Word word = Word.builder().value("testen").build();
        Round round = Round.builder().word(word).build();
        Try tryInst = Try.builder().value("bieren").round(round).build();

        boolean isCorrect = tryInst.isCorrect();

        assertFalse(isCorrect);
    }
}