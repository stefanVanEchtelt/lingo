package com.lingo.project.word.infastructure.driver.service;

import com.lingo.project.word.core.domain.Word;
import com.lingo.project.word.core.ports.WordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordService {
    private final WordStorage wordStorage;

    @Autowired
    public WordService(WordStorage wordStorage) {
        this.wordStorage = wordStorage;
    }

    public Word findRandomWordByLength(int length) {
        return this.wordStorage.findRandomWordByLength(length);
    }
}
