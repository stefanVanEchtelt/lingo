package com.lingo.project.word.core.application;

import com.lingo.project.word.core.domain.Word;
import com.lingo.project.word.core.domain.WordFilter;
import com.lingo.project.word.core.ports.WordStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WordProcessor implements WordProcessorInterface {
    private final WordFilter wordFilter;
    private final WordStorage wordStorage;

    public void addSingle(String wordValue) {
        if (this.wordFilter.verify(wordValue)) {
            Word word = Word.builder().value(wordValue).isDeleted(false).build();
            this.wordStorage.store(word);
        }
    }

    public void addMultiple(List<String> words) {
        for (String word: words) {
            this.addSingle(word);
        }
    }

    public Word findRandomWordByLength(int length) {
        return this.wordStorage.findRandomWordByLength(length);
    }
}
