package com.lingo.project.word.core.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class LingoWordDifference implements WordDifference {
    private final WordFilter wordFilter;

    @Override
    public WordFeedback check(String word, String checkWord) {
        if (!this.wordFilter.verify(checkWord) || word.length() != checkWord.length()) {
            return this.giveTotalResult(checkWord, LetterDifferenceStatus.INVALID, WordDifferenceStatus.INVALID);
        } else if (word.equals(checkWord)) {
            return this.giveTotalResult(checkWord, LetterDifferenceStatus.CORRECT, WordDifferenceStatus.CORRECT);
        }

        return new WordFeedback(WordDifferenceStatus.CORRECT, new ArrayList<LetterFeedback>());
    }

    private WordFeedback giveTotalResult(String checkWord, LetterDifferenceStatus letterStatus, WordDifferenceStatus wordStatus) {
        List<LetterFeedback> letterFeedback = new ArrayList();

        for (char letter: checkWord.toCharArray()) {
            letterFeedback.add(new LetterFeedback(letter, letterStatus));
        }

        return WordFeedback.builder().letterFeedback(letterFeedback).status(wordStatus).build();
    }
}
