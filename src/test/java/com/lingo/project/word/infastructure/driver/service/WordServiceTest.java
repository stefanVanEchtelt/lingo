package com.lingo.project.word.infastructure.driver.service;

import com.lingo.project.word.core.application.WordChecker;
import com.lingo.project.word.core.application.WordProcessor;
import com.lingo.project.word.core.domain.Word;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WordServiceTest {
    @Test
    void testFindRandomWordByLength() {
        Word word = Word.builder().value("testen").build();
        WordProcessor wordProcessor = mock(WordProcessor.class);
        WordChecker wordChecker = mock(WordChecker.class);
        when(wordProcessor.findRandomWordByLength(6)).thenReturn(word);

        WordService wordService = new WordService(wordProcessor, wordChecker);
        Word wordResult = wordService.findRandomWordByLength(6);

        assertEquals(word, wordResult);
    }
}