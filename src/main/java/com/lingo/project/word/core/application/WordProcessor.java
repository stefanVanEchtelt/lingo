package com.lingo.project.word.core.application;

import com.lingo.project.word.core.domain.Word;
import com.lingo.project.word.core.domain.WordFilter;
import com.lingo.project.word.core.ports.WordWriter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordProcessor {

    private final WordFilter wordFilter;
    private final WordWriter wordWriter;

    public WordProcessor(WordFilter wordFilter, WordWriter wordWriter) {
        this.wordFilter = wordFilter;
        this.wordWriter = wordWriter;
    }

    public void addSingle(String wordValue) {
        if (this.wordFilter.verify(wordValue)) {
            Word word = Word.builder().value(wordValue).build();
            this.wordWriter.writeWord(word);
        }
    }

    public void addMultiple(List<String> words) {
        for (String word: words) {
            this.addSingle(word);
        }
    }
}
