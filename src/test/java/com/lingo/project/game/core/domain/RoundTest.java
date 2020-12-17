package com.lingo.project.game.core.domain;

import com.lingo.project.word.core.domain.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

class RoundTest {
    private Word word;

    @BeforeEach
    void beforeEach () {
        this.word = Word.builder().value("testen").build();
    }

    @Test
    void hasAnsweredCorrectly() {
        List<Try> tries = new ArrayList<Try>();
        Round round = Round.builder().word(this.word).build();
        tries.add(Try.builder().value("testen").round(round).build());
        round.setTries(tries);

        boolean correct = round.hasAnsweredCorrectly();

        assertTrue(correct);
    }

    @Test
    void hasNotAnsweredCorrectly() {
        List<Try> tries = new ArrayList<Try>();
        Round round = Round.builder().word(this.word).build();
        tries.add(Try.builder().value("tester").round(round).build());
        round.setTries(tries);

        boolean correct = round.hasAnsweredCorrectly();

        assertFalse(correct);
    }

    @Test
    void roundNoTriesCheckStartStatus() {
        Round round = Round.builder().word(this.word).build();
        Round round2 = Round.builder().word(this.word).tries(new ArrayList<Try>()).build();

        RoundStatus status = round.getStatus();
        RoundStatus round2Status = round2.getStatus();

        assertEquals(RoundStatus.START, status);
        assertEquals(RoundStatus.START, round2Status);
    }

    @Test
    void roundAnsweredCorrectlyStatusChecker() {
        List<Try> tries = new ArrayList<Try>();
        Round round = Round.builder().word(this.word).build();
        tries.add(Try.builder().value("testen").round(round).build());
        round.setTries(tries);

        RoundStatus status = round.getStatus();

        assertEquals(RoundStatus.WON, status);
    }

    @ParameterizedTest
    @MethodSource("amountOfWrongTriesAndStatus")
    void checkStatusBasedOnAmountOfWrongTries(int amountOfTries, RoundStatus expectedStatus) {
        List<Try> tries = new ArrayList<Try>();
        Round round = Round.builder().word(this.word).build();

        for(int i = 0; i <= amountOfTries - 1; ++i) {
            tries.add(Try.builder().value("wrong").round(round).build());
        }
        round.setTries(tries);

        RoundStatus status = round.getStatus();

        assertEquals(expectedStatus, status);
    }

    static Stream<Arguments> amountOfWrongTriesAndStatus() {
        return Stream.of(
                Arguments.of(0, RoundStatus.START),
                Arguments.of(1, RoundStatus.PROGRESS),
                Arguments.of(2, RoundStatus.PROGRESS),
                Arguments.of(3, RoundStatus.PROGRESS),
                Arguments.of(4, RoundStatus.PROGRESS),
                Arguments.of(5, RoundStatus.LOST)
        );
    }

    @Test
    void nonArgConstructorTest() {
        Round round = new Round();

        assertNull(round.getId());
        assertNull(round.getGame());
        assertNull(round.getWord());
        assertNull(round.getCreatedAt());
    }

    @Test
    void testSetGameAndWord() {
        Round round = new Round();
        Game game = new Game();
        round.setWord(this.word);
        round.setGame(game);

        Word getWord = round.getWord();
        Game getGame = round.getGame();

        assertEquals(this.word, getWord);
        assertEquals(game, getGame);
    }

    @Test
    void emptyReactedInTime() {
        Round round = new Round();

        boolean reactedInTime = round.reactedInTime();

        assertTrue(reactedInTime);
    }

    @Test
    void tryReactedInTime() {
        List<Try> tries = new ArrayList<Try>();
        Round round = Round.builder().word(this.word).build();
        tries.add(Try.builder().value("tester").createdAt(new Timestamp(System.currentTimeMillis())).round(round).build());
        round.setTries(tries);

        boolean reactedInTime = round.reactedInTime();

        assertTrue(reactedInTime);
    }

    @Test
    void tryNotReactedInTime() {
        List<Try> tries = new ArrayList<Try>();
        Round round = Round.builder().word(this.word).build();
        tries.add(Try.builder().value("tester").createdAt(new Timestamp(System.currentTimeMillis() - 11000)).round(round).build());
        round.setTries(tries);

        boolean reactedInTime = round.reactedInTime();

        assertFalse(reactedInTime);
    }

    @Test
    void checkCanAnswerByCorrectTry() {
        List<Try> tries = new ArrayList<Try>();
        Round round = Round.builder().word(this.word).build();
        tries.add(Try.builder().value("testen").round(round).build());
        round.setTries(tries);

        boolean status = round.canAnswer();

        assertFalse(status);
    }


    @ParameterizedTest
    @MethodSource("amountOfWrongTriesAndCanAnswerResult")
    void checkCanAnswerBasedOnAmountOfWrongAnswers(int amountOfTries, boolean expectedBool) {
        List<Try> tries = new ArrayList<Try>();
        Round round = Round.builder().word(this.word).build();

        for(int i = 0; i <= amountOfTries - 1; ++i) {
            tries.add(Try.builder().value("wrong").round(round).build());
        }
        round.setTries(tries);

        boolean status = round.canAnswer();

        assertEquals(expectedBool, status);
    }
    static Stream<Arguments> amountOfWrongTriesAndCanAnswerResult() {
        return Stream.of(
                Arguments.of(0, true),
                Arguments.of(1, true),
                Arguments.of(2, true),
                Arguments.of(3, true),
                Arguments.of(4, true),
                Arguments.of(5, false)
        );
    }
}