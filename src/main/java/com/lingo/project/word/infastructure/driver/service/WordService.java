package com.lingo.project.word.infastructure.driver.service;

import com.lingo.project.word.core.domain.Word;
import com.lingo.project.word.core.ports.WordStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class WordService {
    private final WordStorage wordStorage;

    public Word findRandomWordByLength(int length) {
        return this.wordStorage.findRandomWordByLength(length);
    }
}
