package com.lingo.project.word.core.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordTest {

    @Test
    void wordEmptyObjectTest() {
        Word word = new Word();

        assertNull(word.getId());
        assertNull(word.getValue());
        assertFalse(word.isDeleted());
    }

    @Test
    void wordCheckBuilderOutputSameAsObject() {
        Word wordBuilder = Word.builder().id((long) 1).value("testen").build();
        Word word = new Word((long) 1, "testen", false);

        assertEquals((long) 1, word.getId());
        assertEquals("testen", word.getValue());
        assertFalse(word.isDeleted());
        assertEquals(word, wordBuilder);
        assertEquals(wordBuilder, word);
    }
}