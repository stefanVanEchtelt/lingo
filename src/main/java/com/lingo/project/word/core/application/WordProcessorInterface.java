package com.lingo.project.word.core.application;

import com.lingo.project.word.core.domain.Word;

import java.util.List;

public interface WordProcessorInterface {
    void addSingle(String wordValue);
    void addMultiple(List<String> words);
    Word findRandomWordByLength(int length);
}
