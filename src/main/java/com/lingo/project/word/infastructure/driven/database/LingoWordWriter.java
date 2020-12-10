package com.lingo.project.word.infastructure.driven.database;

import com.lingo.project.word.core.domain.Word;
import com.lingo.project.word.core.ports.WordWriter;
import org.springframework.stereotype.Service;

@Service
public class LingoWordWriter implements WordWriter {

    private final WordRepository wordRepository;

    public LingoWordWriter(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public boolean writeWord(Word word) {
        if (!this.wordRepository.existsByValue(word.getValue())) {
            this.wordRepository.save(word);
            return true;
        }

        return false;
    }
}
