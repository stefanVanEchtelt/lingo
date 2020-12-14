package com.lingo.project.word.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class WordFeedback {
    private WordDifferenceStatus status;
    private List<LetterFeedback> letterFeedback;

    public List<LetterFeedback> getLetterFeedback() {
        Collections.sort(this.letterFeedback, Comparator.comparingInt(LetterFeedback::getIndex));
        return this.letterFeedback;
    }
}
