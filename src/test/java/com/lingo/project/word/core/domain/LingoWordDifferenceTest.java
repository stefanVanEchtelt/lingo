package com.lingo.project.word.core.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LingoWordDifferenceTest {
    private LingoWordDifference difference;

    @BeforeEach
    void beforeEach() {
        this.difference = new LingoWordDifference(new LingoWordFilter());
    }

    @Test
    void checkInvallidWordDifference() {
        List<LetterFeedback> letterFeedbacks = new ArrayList<>();
        String checkWord = "bergem";
        int index = 0;
        for (char letter: checkWord.toCharArray()) {
            letterFeedbacks.add(new LetterFeedback(letter, index, LetterDifferenceStatus.INVALID));
            index++;
        }

        WordFeedback expectedResult = WordFeedback.builder().status(WordDifferenceStatus.INVALID).letterFeedback(letterFeedbacks).build();

        WordFeedback result = this.difference.check("baard", checkWord);
        assertEquals(result, expectedResult);
    }

    @Test
    void checkCorrectWord() {
        List<LetterFeedback> letterFeedbacks = new ArrayList<>();
        String checkWord = "baard";
        int index = 0;
        for (char letter: checkWord.toCharArray()) {
            letterFeedbacks.add(new LetterFeedback(letter, index, LetterDifferenceStatus.CORRECT));
            index++;
        }

        WordFeedback expectedResult = WordFeedback.builder().status(WordDifferenceStatus.CORRECT).letterFeedback(letterFeedbacks).build();

        WordFeedback result = this.difference.check("baard", checkWord);
        assertEquals(result, expectedResult);
    }

    @Test
    void oneLetterCorrectRestAbsent() {
        List<LetterFeedback> letterFeedbacks = new ArrayList<>();
        String checkWord = "bonje";

        letterFeedbacks.add(new LetterFeedback('b', 0, LetterDifferenceStatus.CORRECT));
        letterFeedbacks.add(new LetterFeedback('o', 1, LetterDifferenceStatus.ABSENT));
        letterFeedbacks.add(new LetterFeedback('n', 2, LetterDifferenceStatus.ABSENT));
        letterFeedbacks.add(new LetterFeedback('j', 3, LetterDifferenceStatus.ABSENT));
        letterFeedbacks.add(new LetterFeedback('e', 4, LetterDifferenceStatus.ABSENT));

        WordFeedback expectedResult = WordFeedback.builder().status(WordDifferenceStatus.INCORRECT).letterFeedback(letterFeedbacks).build();
        WordFeedback result = this.difference.check("baard", checkWord);
        assertEquals(result, expectedResult);
    }

    @Test
    void twoLetterCorrectRestAbsent() {
        List<LetterFeedback> letterFeedbacks = new ArrayList<>();
        String checkWord = "barst";

        letterFeedbacks.add(new LetterFeedback('b', 0, LetterDifferenceStatus.CORRECT));
        letterFeedbacks.add(new LetterFeedback('a', 1, LetterDifferenceStatus.CORRECT));
        letterFeedbacks.add(new LetterFeedback('r', 2, LetterDifferenceStatus.PRESENT));
        letterFeedbacks.add(new LetterFeedback('s', 3, LetterDifferenceStatus.ABSENT));
        letterFeedbacks.add(new LetterFeedback('t', 4, LetterDifferenceStatus.ABSENT));

        WordFeedback expectedResult = WordFeedback.builder().status(WordDifferenceStatus.INCORRECT).letterFeedback(letterFeedbacks).build();
        WordFeedback result = this.difference.check("baard", checkWord);
        assertEquals(result, expectedResult);
    }

    @Test
    void correctLettersAndPresentCheck() {
        List<LetterFeedback> letterFeedbacks = new ArrayList<>();
        String checkWord = "draad";

        letterFeedbacks.add(new LetterFeedback('d', 0, LetterDifferenceStatus.ABSENT));
        letterFeedbacks.add(new LetterFeedback('r', 1, LetterDifferenceStatus.PRESENT));
        letterFeedbacks.add(new LetterFeedback('a', 2, LetterDifferenceStatus.CORRECT));
        letterFeedbacks.add(new LetterFeedback('a', 3, LetterDifferenceStatus.PRESENT));
        letterFeedbacks.add(new LetterFeedback('d', 4, LetterDifferenceStatus.CORRECT));

        WordFeedback expectedResult = WordFeedback.builder().status(WordDifferenceStatus.INCORRECT).letterFeedback(letterFeedbacks).build();
        WordFeedback result = this.difference.check("baard", checkWord);
        assertEquals(result, expectedResult);
    }
}