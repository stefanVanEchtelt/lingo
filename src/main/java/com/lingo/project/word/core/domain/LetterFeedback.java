package com.lingo.project.word.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LetterFeedback {
    private char letter;
    private int index;
    private LetterDifferenceStatus feedback;
}
