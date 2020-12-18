package com.lingo.project.word.core.application;

import com.lingo.project.word.core.domain.WordFilter;
import com.lingo.project.word.core.ports.WordReader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class WordImporterTest {
    @Test
    void imports_words_from_a_reader_to_a_writer_using_a_filter() {
        List<String> wordList = List.of(
                "pizza",
                "pizzas",
                "bier"
        );

        WordReader mockReader = mock(WordReader.class);
        when(mockReader.readWords())
                .thenReturn(wordList.stream());

        WordFilter mockFilter = mock(WordFilter.class);
        when(mockFilter.verify(anyString()))
                .thenReturn(true);

        WordProcessorInterface spyWriter = spy(WordProcessorInterface.class);

        WordImporter importer = new WordImporter(
                mockReader,
                mockFilter,
                spyWriter
        );

        importer.importWords();

        verify(spyWriter, times(1)).addMultiple(wordList);
    }
}