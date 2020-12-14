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

        return this.giveWordFeedback(word, checkWord);
    }

    private WordFeedback giveTotalResult(String checkWord, LetterDifferenceStatus letterStatus, WordDifferenceStatus wordStatus) {
        List<LetterFeedback> letterFeedback = new ArrayList();

        int index = 0;
        for (char letter: checkWord.toCharArray()) {
            letterFeedback.add(new LetterFeedback(letter, index, letterStatus));
            index++;
        }

        return WordFeedback.builder().letterFeedback(letterFeedback).status(wordStatus).build();
    }

    private WordFeedback giveWordFeedback(String word, String checkWord) {
        List<LetterFeedback> letterFeedback = new ArrayList();
        char[] checkWordLetters = checkWord.toCharArray();
        char[] correctWordLetters = word.toCharArray();

        int index = 0;
        for (char letter: checkWordLetters) {
            if (letter == word.charAt(index)) {
                letterFeedback.add(new LetterFeedback(letter, index, LetterDifferenceStatus.CORRECT));
                checkWordLetters[index] = ' ';
                correctWordLetters[index] = ' ';
            }
            index++;
        }

        checkWord = String.valueOf(checkWordLetters);
        String correctWord = String.valueOf(correctWordLetters);

        letterFeedback = this.checkIfLetterPresent(letterFeedback, correctWord, checkWord);

        return WordFeedback.builder().letterFeedback(letterFeedback).status(WordDifferenceStatus.INCORRECT).build();
    }

    private List<LetterFeedback> checkIfLetterPresent(List<LetterFeedback> letterFeedback, String correctWord, String checkWord) {
        int index = 0;
        for (Character letter: checkWord.toCharArray()) {
            if (letter != ' ') {
                if (correctWord.contains(letter.toString())) {
                    letterFeedback.add(new LetterFeedback(letter, index, LetterDifferenceStatus.PRESENT));
                } else {
                    letterFeedback.add(new LetterFeedback(letter, index, LetterDifferenceStatus.ABSENT));
                }
            }
            index++;
        }

        return letterFeedback;
    }
}
