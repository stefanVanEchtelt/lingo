package com.lingo.project.word.core.ports;

import com.lingo.project.word.core.domain.Word;

public interface WordWriter {
    boolean writeWord(Word word);
}
