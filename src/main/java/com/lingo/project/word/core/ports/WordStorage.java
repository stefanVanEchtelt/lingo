package com.lingo.project.word.core.ports;

import com.lingo.project.word.core.domain.Word;
import org.springframework.stereotype.Service;

@Service
public interface WordStorage {
    boolean store(Word word);
    Word findRandomWordByLength(int length);
}
