package com.lingo.project.word.core.application;

import com.lingo.project.word.core.domain.WordFilter;
import com.lingo.project.word.core.ports.WordReader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class WordImporter {
    private final WordReader reader;
    private final WordFilter filter;
    private final WordProcessorInterface wordProcessor;

    public void importWords() {
        List<String> filteredWords = reader.readWords()
                .filter(filter::verify)
                .collect(toList());

        wordProcessor.addMultiple(filteredWords);
    }
}
