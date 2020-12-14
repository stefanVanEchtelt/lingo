package com.lingo.project.word.core.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LingoWordFilterTest {
    private LingoWordFilter filter;

    @BeforeEach
    void beforeEach() {
        this.filter = new LingoWordFilter();
    }

    @ParameterizedTest
    @MethodSource("provideWordsOfVaryingLength")
    void acceptsWordsOf5Or6Or7LettersOnly(String word, boolean shouldAccept) {
        boolean accepts = this.filter.verify(word);
        assertEquals(shouldAccept, accepts);
    }

    @ParameterizedTest
    @MethodSource("provideWordsOfVaryingCaseSymbols")
    void acceptsWordsOfLowercaseLettersOnly(String word, boolean shouldAccept) {
        boolean accepts = this.filter.verify(word);
        assertEquals(shouldAccept, accepts);
    }

    static Stream<Arguments> provideWordsOfVaryingLength() {
        return Stream.of(
                Arguments.of("pizza", true),
                Arguments.of("pizzas", true),
                Arguments.of("backend", true),
                Arguments.of("beer", false),
                Arguments.of("software", false),
                Arguments.of("developer", false)
        );
    }

    static Stream<Arguments> provideWordsOfVaryingCaseSymbols() {
        return Stream.of(
                Arguments.of("pizza", true),
                Arguments.of("Pizza", false),
                Arguments.of("PiZZa", false),
                Arguments.of("PIZZA", false),
                Arguments.of("pizzA", false),
                Arguments.of("piZza", false),
                Arguments.of("piZzä", false),
                Arguments.of("p!zza", false),
                Arguments.of("P!ZZA", false),
                Arguments.of("piZ a", false),
                Arguments.of("p̣̝̔̂͝i̻͚ͅz̰̟̖̩̩̘z̖̭̞͉͍̕a͖͛̓͘", false)
        );
    }
}