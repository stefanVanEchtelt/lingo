package com.lingo.project.word.core.application;

import com.lingo.project.word.core.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class WordChecker {
    private final WordDifference wordDifference;

    public WordFeedback checkDifference(Word word, String guessWord) {
        return this.wordDifference.check(word.getValue(), guessWord);
    }
}
