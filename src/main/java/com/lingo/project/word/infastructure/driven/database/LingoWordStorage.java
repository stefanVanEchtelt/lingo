package com.lingo.project.word.infastructure.driven.database;

import com.lingo.project.word.core.domain.Word;
import com.lingo.project.word.core.ports.WordStorage;
import org.springframework.stereotype.Service;

@Service
public class LingoWordStorage implements WordStorage {

    private final WordRepository wordRepository;

    public LingoWordStorage(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public boolean store(Word word) {
        if (!this.wordRepository.existsByValue(word.getValue())) {
            this.wordRepository.save(word);
            return true;
        }

        return false;
    }

    @Override
    public Word findRandomWordByLength(int length) {
        return this.wordRepository.findRandomByWordLength(length);
    }
}
