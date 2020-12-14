package com.lingo.project.word.core.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

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
        for (char letter: checkWord.toCharArray()) {
            letterFeedbacks.add(new LetterFeedback(letter, LetterDifferenceStatus.INVALID));
        }

        WordFeedback expectedResult = WordFeedback.builder().status(WordDifferenceStatus.INVALID).letterFeedback(letterFeedbacks).build();

        WordFeedback result = this.difference.check("baard", checkWord);
        assertEquals(result, expectedResult);
    }

    @Test
    void checkCorrectWord() {
        List<LetterFeedback> letterFeedbacks = new ArrayList<>();
        String checkWord = "baard";
        for (char letter: checkWord.toCharArray()) {
            letterFeedbacks.add(new LetterFeedback(letter, LetterDifferenceStatus.CORRECT));
        }

        WordFeedback expectedResult = WordFeedback.builder().status(WordDifferenceStatus.CORRECT).letterFeedback(letterFeedbacks).build();

        WordFeedback result = this.difference.check("baard", checkWord);
        assertEquals(result, expectedResult);
    }

//        bergen
//        bonje
//        barst
//        draad
//        baard
//    }
}