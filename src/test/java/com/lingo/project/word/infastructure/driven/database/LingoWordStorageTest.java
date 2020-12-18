package com.lingo.project.word.infastructure.driven.database;

import com.lingo.project.word.core.domain.Word;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LingoWordStorageTest {

    @Test
    void testGetRandomWordByLength() {
        Word word = Word.builder().value("testen").build();
        WordRepository wordRepository = mock(WordRepository.class);
        when(wordRepository.findRandomByWordLength(6)).thenReturn(word);

        LingoWordStorage lingoWordStorage = new LingoWordStorage(wordRepository);
        Word wordResult = lingoWordStorage.findRandomWordByLength(6);

        assertEquals(word, wordResult);
    }

    @Test
    void testStoreWordNotExists() {
        Word word = Word.builder().value("testen").build();
        WordRepository wordRepository = mock(WordRepository.class);
        when(wordRepository.existsByValue(word.getValue())).thenReturn(false);
        when(wordRepository.save(word)).thenReturn(word);

        LingoWordStorage lingoWordStorage = new LingoWordStorage(wordRepository);
        boolean result = lingoWordStorage.store(word);

        assertTrue(result);

    }

    @Test
    void testStoreWordExists() {
        Word word = Word.builder().value("testen").build();
        WordRepository wordRepository = mock(WordRepository.class);
        when(wordRepository.existsByValue(word.getValue())).thenReturn(true);

        LingoWordStorage lingoWordStorage = new LingoWordStorage(wordRepository);
        boolean result = lingoWordStorage.store(word);

        assertFalse(result);
    }
}