package com.lingo.project.word.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class WordFeedback {
    private WordDifferenceStatus status;
    private List<LetterFeedback> letterFeedback;
}
