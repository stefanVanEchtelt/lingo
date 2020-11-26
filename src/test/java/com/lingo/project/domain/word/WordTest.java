package com.lingo.project.domain.word;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordTest {

    @Test
    void isCorrectSize5DigitsTest() {
        Word fiveDigitsWord = Word.builder().value("afdak").build();
        assertTrue(fiveDigitsWord.isValid());
        assertTrue(fiveDigitsWord.isCorrectSize());
    }

    @Test
    void isCorrectSizeLowerThan5DigitsTest() {
        Word lowerThen5DigitsWord = Word.builder().value("test").build();
        assertFalse(lowerThen5DigitsWord.isValid());
        assertFalse(lowerThen5DigitsWord.isCorrectSize());
    }

    @Test
    void isCorrectSize7DigitsTest() {
        Word sevenDigitsWord = Word.builder().value("rasdier").build();
        assertTrue(sevenDigitsWord.isValid());
        assertTrue(sevenDigitsWord.isCorrectSize());
    }

    @Test
    void isCorrectSizeLargerThan7DigitsFalseTest() {
        Word largerThen7DigitsWord = Word.builder().value("aanbelde").build();
        assertFalse(largerThen7DigitsWord.isValid());
        assertFalse(largerThen7DigitsWord.isCorrectSize());
    }

    @Test
    void isValidNullTest() {
        Word nullWord = Word.builder().value(null).build();
        assertFalse(nullWord.isValid());
    }

    @Test
    void isUpperCaseTest() {
        Word uppercaseWord = Word.builder().value("Utrecht").build();
        Word LowerCaseWord = Word.builder().value("rechter").build();
        Word UpperCaseinMidWord = Word.builder().value("recHter").build();

        assertFalse(uppercaseWord.isValid());
        assertTrue(uppercaseWord.isUpperCase());

        assertTrue(LowerCaseWord.isValid());
        assertFalse(LowerCaseWord.isUpperCase());

        assertFalse(UpperCaseinMidWord.isValid());
        assertTrue(UpperCaseinMidWord.isUpperCase());
    }

    @Test
    void hasSpecialCarsTest() {
        Word dotWord = Word.builder().value("test.").build();
        Word slashWord = Word.builder().value("te/st").build();
        Word punctionWord = Word.builder().value("Pathé").build();
        Word normal = Word.builder().value("testen").build();


        assertFalse(dotWord.isValid());
        assertTrue(dotWord.hasSpecialCars());

        assertFalse(slashWord.isValid());
        assertTrue(slashWord.hasSpecialCars());

        assertFalse(punctionWord.isValid());
        assertTrue(punctionWord.hasSpecialCars());

        assertTrue(normal.isValid());
        assertFalse(normal.hasSpecialCars());
    }
}