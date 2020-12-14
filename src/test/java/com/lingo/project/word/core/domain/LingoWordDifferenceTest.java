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
    void checkWordDifference() {
        List<LetterFeedback> letterFeedbacks = new ArrayList<>();
        letterFeedbacks.add(new LetterFeedback('b', LetterDifferenceStatus.INVALID));
        letterFeedbacks.add(new LetterFeedback('e', LetterDifferenceStatus.INVALID));
        letterFeedbacks.add(new LetterFeedback('r', LetterDifferenceStatus.INVALID));
        letterFeedbacks.add(new LetterFeedback('g', LetterDifferenceStatus.INVALID));
        letterFeedbacks.add(new LetterFeedback('e', LetterDifferenceStatus.INVALID));
        letterFeedbacks.add(new LetterFeedback('n', LetterDifferenceStatus.INVALID));

        WordFeedback expectedResult = WordFeedback.builder().status(WordDifferenceStatus.INVALID).letterFeedback(letterFeedbacks).build();

        WordFeedback result = this.difference.check("baard", "bergen");
        assertEquals(result, expectedResult);
    }

//    static Stream<Arguments> provideWordsAndResult() {
//        return Stream.of(
//                Arguments.of("baard", "bergen", new HashMap<Character, LetterDifferenceStatus>().put("b", )),
//        );


//        bergen
//        bonje
//        barst
//        draad
//        baard
//    }
}