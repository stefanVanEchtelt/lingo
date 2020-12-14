package com.lingo.project.word.infastructure.driver.service;

import com.lingo.project.word.core.application.WordChecker;
import com.lingo.project.word.core.application.WordProcessor;
import com.lingo.project.word.core.domain.Word;
import com.lingo.project.word.core.domain.WordFeedback;
import com.lingo.project.word.core.ports.WordStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class WordService {
    private final WordProcessor wordProcessor;
    private final WordChecker wordChecker;

    public Word findRandomWordByLength(int length) {
        return this.wordProcessor.findRandomWordByLength(length);
    }

    public WordFeedback checkIfWordsAreTheSame(Word word, String guessWord) {
        return this.wordChecker.checkDifference(word, guessWord);
    }
}
